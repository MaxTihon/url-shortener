package com.github.maxtihon.urlshortener.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredAtRequest {
    private String shortURL;
    private int daysToExpired;
}
