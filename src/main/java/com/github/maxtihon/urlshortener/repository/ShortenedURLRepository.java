package com.github.maxtihon.urlshortener.repository;

import com.github.maxtihon.urlshortener.model.ShortenedURL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface ShortenedURLRepository extends CrudRepository<ShortenedURL, String> {
    ShortenedURL findByOriginalURL(String originalURL);

    ShortenedURL findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE shortened_url AS u SET u.redirects = u.redirects + 1 WHERE u.token = :token", nativeQuery = true)
    void increaseRedirects(@Param("token") String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE shortened_url AS u SET u.expired_at = :expiredAt WHERE u.token = :token", nativeQuery = true)
    void setExpiredAt(@Param("token") String token, @Param("expiredAt") Date expiredAt);
}
