package es.codeurjc.ads.controller;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.codeurjc.ads.model.Ad;
import es.codeurjc.ads.service.AdsService;
import es.codeurjc.ads.service.ForbiddenWordsException;

@Controller
public class AdsController {

    @Autowired
    private AdsService service;

    @PostConstruct
    public void init() throws ForbiddenWordsException {
        service.save(new Ad("Peter", "I sell my car", "Cheap!!"));
        service.save(new Ad("John", "I need a car", "I have money"));
    }

    @GetMapping("/")
    public String ads(Model model) {
        model.addAttribute("ads", service.findAll());
        return "ads";
    }

    @GetMapping("/{id}")
    public String getAd(@PathVariable long id, Model model) {
        Ad ad = service.findById(id);
        if (ad != null) {
            model.addAttribute("ad", ad);
            return "ad";
        } else {
            return "not_found";
        }
    }

    @PostMapping("/")
    public String newAd(@RequestBody Ad ad, Model model) {
        try {
            service.save(ad);
            model.addAttribute("ad", ad);
            return "ad";
        } catch (ForbiddenWordsException e) {
            return "bad_request";
        }
    }

    @PutMapping("/{id}")
    public String replaceAd(@PathVariable long id, @RequestBody Ad newAd, Model model) throws ForbiddenWordsException {
        Ad ad = service.findById(id);
        if (ad != null) {
            newAd.setId(id);
            service.save(newAd);
            model.addAttribute("ad", newAd);
            return "ad";
        } else {
            return "not_found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAd(@PathVariable long id, Model model) {
        Ad ad = service.remove(id);
        if (ad != null) {
            model.addAttribute("ad", ad);
            return "ad";
        } else {
            return "not_found";
        }
    }
}