package com.github.maxtihon.urlshortener.service;

import com.github.maxtihon.urlshortener.controller.dto.URLStatsResponse;
import com.github.maxtihon.urlshortener.model.ShortenedURL;
import com.github.maxtihon.urlshortener.repository.ShortenedURLRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class ShortenedURLService {
    public static final String REDIRECTION_PREFIX = "http://localhost:8080/";

    private final ShortenedURLRepository repository;

    @Autowired
    public ShortenedURLService(ShortenedURLRepository repository) {
        this.repository = repository;
    }

    public String generateShortURL(String originalURL) {
        ShortenedURL shortenedURL = repository.findByOriginalURL(originalURL);

        return Objects.requireNonNullElseGet(shortenedURL, () -> generateAndReturnShortURL(originalURL)).getToken();
    }

    public String getOriginalURL(String token) {
        ShortenedURL shortenedURL = repository.findByToken(token);

        if (shortenedURL != null && !shortenedURL.isExpired(new Date())) {
            repository.increaseRedirects(token);
            return shortenedURL.getOriginalURL();
        } else {
            throw new TokenNotFoundException(token);
        }
    }

    public URLStatsResponse getURLStatsByToken(String token) {
        ShortenedURL shortenedURL = repository.findByToken(token);

        if (shortenedURL != null) {
            return new URLStatsResponse(shortenedURL.getToken()
                    , shortenedURL.getOriginalURL()
                    , shortenedURL.getRedirects()
                    , shortenedURL.getCreatedAt()
                    , shortenedURL.getExpiredAt());
        } else {
            throw new TokenNotFoundException(token);
        }
    }

    public void deleteRegisteredURL(String token) {
        ShortenedURL shortenedURL = repository.findByToken(token);

        if (shortenedURL != null) {
            repository.delete(shortenedURL);
        } else {
            throw new TokenNotFoundException(token);
        }
    }

    public void setExpiredAt(String token, int daysToExpired) {
        ShortenedURL shortenedURL = repository.findByToken(token);

        if (shortenedURL != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(shortenedURL.getCreatedAt());
            instance.add(Calendar.DATE, daysToExpired);

            repository.setExpiredAt(token, instance.getTime());

            ShortenedURL updated = repository.findByToken(token);
        } else {
            throw new TokenNotFoundException(token);
        }
    }

    private ShortenedURL generateAndReturnShortURL(String originalURL) {
        Hashids hashids = new Hashids(originalURL, 5, "0123456789abcdefghijklmnopqrstuvwxyz");
        String token = hashids.encode(12345L);

        ShortenedURL shortenedURL = new ShortenedURL();
        shortenedURL.setToken(token);
        shortenedURL.setOriginalURL(originalURL);
        shortenedURL.setRedirects(0L);

        return repository.save(shortenedURL);
    }
}
