package es.codeurjc.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.codeurjc.ads.service.AdsRepository;
import es.codeurjc.ads.service.AdsServiceImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public AdsServiceImpl adsService(AdsRepository repository){
		return new AdsServiceImpl(repository);
	}
}
