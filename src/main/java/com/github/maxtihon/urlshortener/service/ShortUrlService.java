package com.github.maxtihon.urlshortener.service;

import com.github.maxtihon.urlshortener.dto.GenerateRequest;
import com.github.maxtihon.urlshortener.model.ShortUrl;

public interface ShortUrlService {
    ShortUrl generateShortUrl(GenerateRequest generateRequest);
    ShortUrl getOriginalUrlForRedirect(String token);
    ShortUrl getShortUrlStats(String token);
    void deleteShortUrl(String token);
}
