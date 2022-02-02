package com.github.maxtihon.urlshortener.controller;

import com.github.maxtihon.urlshortener.service.ShortenedURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedirectController {
    private final ShortenedURLService shortenedURLService;

    @Autowired
    public RedirectController(ShortenedURLService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @GetMapping(path = "/{token}")
    public RedirectView redirectToOriginalURL(@PathVariable("token") String token, HttpServletRequest httpServletRequest) {
        String originalURL = shortenedURLService.getOriginalURL(token);


        return new RedirectView(originalURL);
    }
}
