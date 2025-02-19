package es.codeurjc.ads.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.ads.model.Ad;

@Service
public class AdsServiceImpl implements AdsService {

    @Autowired
    private AdsRepository repository;

    public void save(Ad ad) throws ForbiddenWordsException {

        if (containsForbiddenWords(ad)) {
            throw new ForbiddenWordsException();
        }

        repository.save(ad);
    }

    private boolean containsForbiddenWords(Ad ad) {
        return containsForbiddenWords(ad.getTitle()) || containsForbiddenWords(ad.getComment());
    }

    private boolean containsForbiddenWords(String text) {
        String[] forbidenWords = { "VIOLENCE", "SEX" };
        for (String word : forbidenWords) {
            if (text.toUpperCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    public Collection<Ad> findAll() {
        return repository.findAll();
    }

    public Ad findById(long id) {
        return repository.findById(id);
    }    

    public Ad remove(long id) {
        return repository.remove(id);
    }
}
