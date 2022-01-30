package com.github.maxtihon.urlshortener.controller;

import com.github.maxtihon.urlshortener.controller.dto.GenerateRequest;
import com.github.maxtihon.urlshortener.controller.dto.GenerateResponse;
import com.github.maxtihon.urlshortener.service.ShortenedURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenerateController {
    private final ShortenedURLService shortenedURLService;

    @Autowired
    public GenerateController(ShortenedURLService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @PostMapping(path = "/generate", consumes = "application/json")
    public GenerateResponse generateShortUrl(@RequestBody GenerateRequest generateRequest) {
        String originalURL = generateRequest.getOriginalURL();
        String token = shortenedURLService.generateShortURL(originalURL);

        return new GenerateResponse(ShortenedURLService.REDIRECTION_PREFIX, token);
    }
}
