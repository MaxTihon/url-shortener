package com.github.maxtihon.urlshortener.controller;

import com.github.maxtihon.urlshortener.controller.dto.URLStatsRequest;
import com.github.maxtihon.urlshortener.controller.dto.URLStatsResponse;
import com.github.maxtihon.urlshortener.service.ShortenedURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    private final ShortenedURLService shortenedURLService;

    @Autowired
    public StatsController(ShortenedURLService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @PostMapping(value = "/stats", consumes = "application/json")
    public URLStatsResponse getURLStatsByShortURL(@RequestBody URLStatsRequest urlStatsRequest) {
        String token = urlStatsRequest.getShortURL().split(RedirectController.REDIRECTION_PREFIX)[1];

        return shortenedURLService.getURLStatsByToken(token);
    }
    
}
