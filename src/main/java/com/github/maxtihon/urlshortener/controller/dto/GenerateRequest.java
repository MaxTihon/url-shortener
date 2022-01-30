package com.github.maxtihon.urlshortener.controller.dto;

import lombok.Data;

@Data
public class GenerateRequest {
    private final String originalURL;
}
