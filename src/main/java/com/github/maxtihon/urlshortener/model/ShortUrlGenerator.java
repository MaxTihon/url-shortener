package com.github.maxtihon.urlshortener.model;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ShortUrlGenerator {
    @Value("${hashids.alphabet}")
    private String hashidsAlphabet;
    @Value("${hashids.min-hash-length}")
    private int minHashLength;
    @Value("${hashids.encode-numbers}")
    private Long encodeNumbers;

    public ShortUrl generateShortUrl(String originalUrl) {
        Hashids hashids = new Hashids(originalUrl, minHashLength, hashidsAlphabet);
        String token = hashids.encode(encodeNumbers);

        return new ShortUrl(
                token
                , originalUrl
                , OffsetDateTime.now()
        );
    }
}
