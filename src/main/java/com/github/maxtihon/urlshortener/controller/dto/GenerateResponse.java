package com.github.maxtihon.urlshortener.controller.dto;

import lombok.Value;

@Value
public class GenerateResponse {
    String shortURL;

    public GenerateResponse(String prefix, String token) {
        this.shortURL = prefix + token;
    }
}
