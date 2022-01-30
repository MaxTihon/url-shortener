package com.github.maxtihon.urlshortener.controller.dto;


import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class URLStatsResponse {
    private final String token;
    private final String originalURL;
    private final Long redirects;
    private final Date createdAt;
}
