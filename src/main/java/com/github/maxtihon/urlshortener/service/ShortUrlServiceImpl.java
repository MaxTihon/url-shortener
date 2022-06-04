package com.github.maxtihon.urlshortener.service;

import com.github.maxtihon.urlshortener.dto.GenerateRequest;
import com.github.maxtihon.urlshortener.model.ShortUrl;
import com.github.maxtihon.urlshortener.model.ShortUrlGenerator;
import com.github.maxtihon.urlshortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository repository;
    private final ShortUrlGenerator generator;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlRepository repository, ShortUrlGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    @Override
    @Transactional
    public ShortUrl generateShortUrl(GenerateRequest generateRequest) {
        String originalUrl = generateRequest.getOriginalUrl();
        OffsetDateTime expiredAt = generateRequest.getExpiredAt();

        ShortUrl shortUrl = generator.generateShortUrl(originalUrl);

        if (expiredAt != null) {
            shortUrl.setExpirationDate(expiredAt);
        }

        return repository.save(shortUrl);
    }

    @Override
    public ShortUrl getOriginalUrlForRedirect(String token) {
        ShortUrl shortUrl = repository.findByToken(token);

        if (shortUrl == null) {
            throw new IllegalStateException("Token not found. Short URL not exist");
        }

        if (!shortUrl.isExpired()) {
            shortUrl.increaseRedirects();
            repository.save(shortUrl);
            return shortUrl;
        } else {
            throw new IllegalStateException("The link has expired");
        }
    }

    @Override
    public ShortUrl getShortUrlStats(String token) {
        ShortUrl shortUrl = repository.findByToken(token);

        if (shortUrl != null) {
            return shortUrl;
        } else {
            throw new IllegalStateException("Token not found. Short URL not exist");
        }
    }

    @Override
    @Transactional
    public void deleteShortUrl(String token) {
        repository.deleteByToken(token);
    }
}
