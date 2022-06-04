package com.github.maxtihon.urlshortener.dto;

import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class ShortUrlDto {
    String token;
    String originalUrl;
    OffsetDateTime createdAt;
    OffsetDateTime expiredAt;
    Long redirects;
}
