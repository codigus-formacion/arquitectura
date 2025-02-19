package es.codeurjc.ads.service;

import java.util.Collection;

import es.codeurjc.ads.model.Ad;

// Secondary Port

public interface AdsRepository {

    public void save(Ad ad);

    public Collection<Ad> findAll();

    public Ad findById(long id);

    public Ad remove(long id);

}
