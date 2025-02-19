package es.codeurjc.ads.repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.ads.model.Ad;
import es.codeurjc.ads.service.AdsRepository;

//Secondary Adapter

@Service
public class AdsMemoryRepository implements AdsRepository {

    private Map<Long, Ad> ads = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public void save(Ad ad) {
        ad.setId(lastId.incrementAndGet());
        ads.put(ad.getId(), ad);
    }

    public Collection<Ad> findAll() {
        return ads.values();
    }

    public Ad findById(long id) {
        return ads.get(id);
    }

    public Ad remove(long id) {
        return ads.remove(id);
    }
}

