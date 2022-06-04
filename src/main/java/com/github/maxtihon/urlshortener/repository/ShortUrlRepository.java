package com.github.maxtihon.urlshortener.repository;

import com.github.maxtihon.urlshortener.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
    ShortUrl findByToken(String token);
    void deleteByToken(String token);
}
