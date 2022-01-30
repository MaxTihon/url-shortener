package com.github.maxtihon.urlshortener.controller;

import com.github.maxtihon.urlshortener.controller.dto.*;
import com.github.maxtihon.urlshortener.service.ShortenedURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    private final ShortenedURLService shortenedURLService;

    @Autowired
    public AdminController(ShortenedURLService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @DeleteMapping(path = "/admin", consumes = "application/json")
    public void deleteRegisteredURL(@RequestBody DeleteRequest deleteRequest) {
        String token = deleteRequest.getShortURL().split(ShortenedURLService.REDIRECTION_PREFIX)[1];

        shortenedURLService.deleteRegisteredURL(token);
    }

    @PostMapping(path = "/admin", consumes = "application/json")
    public URLStatsResponse getURLStatsByShortURL(@RequestBody URLStatsRequest urlStatsRequest) {
        String token = urlStatsRequest.getShortURL().split(ShortenedURLService.REDIRECTION_PREFIX)[1];

        return shortenedURLService.getURLStatsByToken(token);
    }

    @PatchMapping(path = "/admin", consumes = "application/json")
    public void setExpiredAt(@RequestBody ExpiredAtRequest expiredAtRequest) {
        String token = expiredAtRequest.getShortURL().split(ShortenedURLService.REDIRECTION_PREFIX)[1];

        shortenedURLService.setExpiredAt(token, expiredAtRequest.getDaysToExpired());
    }
}
