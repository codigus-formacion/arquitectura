package es.codeurjc.ads.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.codeurjc.ads.model.Ad;

public class AdsServiceTest {

    private AdsServiceImpl service;
    private AdsRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = mock(AdsRepository.class);
        this.service = new AdsServiceImpl(repository);
    }

    @Test
    public void givenValidAd_whenIsSaved_thenIsSavedToRepo() throws ForbiddenWordsException {
        
        Ad ad = new Ad("author", "title", "comment");

        this.service.save(ad);

        verify(repository).save(ad);
    }

    @Test
    public void givenNotValidAd_whenIsSaved_thenExceptionAndNotSavedToRepo() throws ForbiddenWordsException {
        
        Ad ad = new Ad("author_sex", "title_violence", "comment");

        assertThrows(ForbiddenWordsException.class, () -> {
            this.service.save(ad);
        });

        verify(repository, never()).save(ad);
    }
}