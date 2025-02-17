package es.codeurjc.ads;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ads")
public class AdsController {

	private Map<Long, Ad> ads = new ConcurrentHashMap<>();
	private AtomicLong lastId = new AtomicLong();

	public AdsController() {

		Ad ad1 = new Ad("Peter", "I sell my car", "Cheap!!");
		ad1.setId(lastId.incrementAndGet());
		ads.put(ad1.getId(), ad1);

		Ad ad2 = new Ad("John", "I need a car", "I have money");
		ad2.setId(lastId.incrementAndGet());
		ads.put(ad2.getId(), ad2);		
	}

	@GetMapping("/")
	public Collection<Ad> ads() {
		return ads.values();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ad> getAd(@PathVariable long id) {

		Ad ad = ads.get(id);

		if (ad != null) {
			return ResponseEntity.ok(ad);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Ad> newAd(@RequestBody Ad ad) {

		if(containsForbiddenWords(ad)){
			return ResponseEntity.badRequest().build();
		}

		long id = lastId.incrementAndGet();
		ad.setId(id);
		ads.put(id, ad);

		return ResponseEntity.ok(ad);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ad> replaceAd(@PathVariable long id, @RequestBody Ad newAd) {

		if(containsForbiddenWords(newAd)){
			return ResponseEntity.badRequest().build();
		}

		Ad ad = ads.get(id);

		if (ad != null) {

			newAd.setId(id);
			ads.put(id, newAd);

			return ResponseEntity.ok(newAd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private boolean containsForbiddenWords(Ad ad){
		return containsForbiddenWords(ad.getTitle()) || containsForbiddenWords(ad.getComment());
	}

	private boolean containsForbiddenWords(String text){		
		String[] forbidenWords = { "VIOLENCE", "SEX" };
		for(String word : forbidenWords){
			if(text.toUpperCase().contains(word)){
				return true;
			}
		}
		return false;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Ad> deleteAd(@PathVariable long id) {

		Ad ad = ads.remove(id);

		if (ad != null) {
			return ResponseEntity.ok(ad);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
