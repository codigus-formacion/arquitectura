package es.codeurjc.ads.service;

import java.util.Collection;

import es.codeurjc.ads.model.Ad;

//Primary Port

public interface AdsService {

    public void save(Ad ad) throws ForbiddenWordsException;

    public Collection<Ad> findAll();

    public Ad findById(long id);

    public Ad remove(long id);
}
