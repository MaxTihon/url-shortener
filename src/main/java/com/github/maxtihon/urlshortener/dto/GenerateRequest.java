package com.github.maxtihon.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class GenerateRequest {
    String originalURL;
    OffsetDateTime expiredAt;
}
