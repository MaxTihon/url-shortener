package com.github.maxtihon.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Value
public class GenerateRequest {
    String originalUrl;
    OffsetDateTime expiredAt;
}
