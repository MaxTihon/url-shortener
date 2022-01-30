package com.github.maxtihon.urlshortener.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(String token) {
        super(String.format("Link %s not found", token));
    }
}
