package es.codeurjc.ads.controller;

import java.util.Collection;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.ads.model.Ad;
import es.codeurjc.ads.service.AdsService;
import es.codeurjc.ads.service.ForbiddenWordsException;

@RestController
@RequestMapping("/ads")
public class AdsController {

	@Autowired
	private AdsService service;

	@PostConstruct
	public void init() throws ForbiddenWordsException {
		service.save(new Ad("Peter", "I sell my car", "Cheap!!"));
		service.save(new Ad("John", "I need a car", "I have money"));
	}

	@GetMapping("/")
	public Collection<Ad> ads() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ad> getAd(@PathVariable long id) {

		Ad ad = service.findById(id);

		if (ad != null) {
			return ResponseEntity.ok(ad);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Ad> newAd(@RequestBody Ad ad) {

		try {
			service.save(ad);
		} catch(ForbiddenWordsException e){
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(ad);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ad> replaceAd(@PathVariable long id, @RequestBody Ad newAd) throws ForbiddenWordsException {

		Ad ad = service.findById(id);

		if (ad != null) {

			newAd.setId(id);
			service.save(newAd);

			return ResponseEntity.ok(newAd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<Ad> deleteAd(@PathVariable long id) {

		Ad ad = service.remove(id);

		if (ad != null) {
			return ResponseEntity.ok(ad);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
