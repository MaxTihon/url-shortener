package com.github.maxtihon.urlshortener.dto;

import lombok.Value;

@Value
public class GenerateResponse {
    String shortUrl;

    public GenerateResponse(String prefix, String token) {
        this.shortUrl = prefix + token;
    }
}
