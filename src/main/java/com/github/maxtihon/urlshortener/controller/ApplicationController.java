package com.github.maxtihon.urlshortener.controller;

import com.github.maxtihon.urlshortener.dto.GenerateRequest;
import com.github.maxtihon.urlshortener.dto.GenerateResponse;
import com.github.maxtihon.urlshortener.dto.ShortUrlDto;
import com.github.maxtihon.urlshortener.model.ShortUrl;
import com.github.maxtihon.urlshortener.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ApplicationController {
    private final ShortUrlService shortUrlService;

    @Autowired
    public ApplicationController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping(path = "/app", consumes = "application/json")
    public ResponseEntity<GenerateResponse> generateShortUrl(@RequestBody GenerateRequest generateRequest, HttpServletRequest request) {
        ShortUrl shortUrl = shortUrlService.generateShortUrl(generateRequest);

        String redirectionPrefix = request.getRequestURL().toString().split("app")[0];

        return new ResponseEntity<>(new GenerateResponse(redirectionPrefix, shortUrl.getToken()), HttpStatus.CREATED);
    }

    @GetMapping(path = "/app/{token}", consumes = "application/json")
    public ResponseEntity<ShortUrlDto> getShortUrlStats(@PathVariable("token") String token) {
        ShortUrl shortUrl = shortUrlService.getShortUrlStats(token);

        return new ResponseEntity<>(new ShortUrlDto(
                shortUrl.getToken()
                , shortUrl.getOriginalUrl()
                , shortUrl.getCreatedAt()
                , shortUrl.getExpiredAt()
                , shortUrl.getRedirects()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/app/{token}", consumes = "application/json")
    public ResponseEntity<String> deleteShortUrlByToken(@PathVariable("token") String token) {
        shortUrlService.deleteShortUrl(token);

        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{token}")
    public RedirectView redirectToOriginalUrl(@PathVariable("token") String token) {
        ShortUrl shortUrl = shortUrlService.getOriginalUrlForRedirect(token);

        return new RedirectView(shortUrl.getOriginalUrl());
    }
}
