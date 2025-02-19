package es.codeurjc.ads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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
    public String ads(Model model) {
        model.addAttribute("ads", ads.values());
        return "ads";
    }

    @GetMapping("/{id}")
    public String getAd(@PathVariable long id, Model model) {
        Ad ad = ads.get(id);
        if (ad != null) {
            model.addAttribute("ad", ad);
            return "ad";
        } else {
            return "not_found";
        }
    }

    @PostMapping("/")
    public String newAd(@RequestBody Ad ad, Model model) {
        if(containsForbiddenWords(ad)){
            return "bad_request";
        }
        long id = lastId.incrementAndGet();
        ad.setId(id);
        ads.put(id, ad);
        model.addAttribute("ad", ad);
        return "ad";
    }

    @PutMapping("/{id}")
    public String replaceAd(@PathVariable long id, @RequestBody Ad newAd, Model model) {
        if(containsForbiddenWords(newAd)){
            return "bad_request";
        }
        Ad ad = ads.get(id);
        if (ad != null) {
            newAd.setId(id);
            ads.put(id, newAd);
            model.addAttribute("ad", newAd);
            return "ad";
        } else {
            return "not_found";
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
    public String deleteAd(@PathVariable long id, Model model) {
        Ad ad = ads.remove(id);
        if (ad != null) {
            model.addAttribute("ad", ad);
            return "ad";
        } else {
            return "not_found";
        }
    }
}