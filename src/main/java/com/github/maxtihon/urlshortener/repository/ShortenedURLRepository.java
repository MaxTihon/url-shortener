package com.github.maxtihon.urlshortener.repository;

import com.github.maxtihon.urlshortener.model.ShortenedURL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ShortenedURLRepository extends CrudRepository<ShortenedURL, String> {
    ShortenedURL findByOriginalURL(String originalURL);
    ShortenedURL findByToken(String token);
    @Transactional
    @Modifying
    @Query(value = "UPDATE shortened_url AS u SET u.redirects = u.redirects + 1 WHERE u.token= ?1", nativeQuery = true)
    void increaseRedirects(String token);
}
