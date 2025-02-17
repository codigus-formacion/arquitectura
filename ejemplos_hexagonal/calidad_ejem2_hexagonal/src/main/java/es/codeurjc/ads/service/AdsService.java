package es.codeurjc.ads.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.ads.model.Ad;

@Service
public class AdsService {

    private Map<Long, Ad> ads = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public void save(Ad ad) throws ForbiddenWordsException {

        if (containsForbiddenWords(ad)) {
            throw new ForbiddenWordsException();
        }

        ad.setId(lastId.incrementAndGet());
        ads.put(ad.getId(), ad);
    }

    public Collection<Ad> findAll() {
        return ads.values();
    }

    public Ad findById(long id) {
        return ads.get(id);
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

    public Ad remove(long id) {
        return ads.remove(id);
    }
}
