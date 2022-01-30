package com.github.maxtihon.urlshortener.controller.dto;


import com.github.maxtihon.urlshortener.controller.RedirectController;

public class GenerateResponse {
    private final String shortURL;

    public GenerateResponse(String token) {
        this.shortURL = RedirectController.REDIRECTION_PREFIX + token;
    }
}
