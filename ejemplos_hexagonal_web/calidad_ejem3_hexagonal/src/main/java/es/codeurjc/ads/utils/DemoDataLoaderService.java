package es.codeurjc.ads.utils;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.ads.model.Ad;
import es.codeurjc.ads.service.AdsService;
import es.codeurjc.ads.service.ForbiddenWordsException;

// Primary Adapter

@Service
public class DemoDataLoaderService {
    
    @Autowired
    private AdsService service;

	@PostConstruct
	public void init() throws ForbiddenWordsException {
		service.save(new Ad("Peter", "I sell my car", "Cheap!!"));
		service.save(new Ad("John", "I need a car", "I have money"));
	}
}
