package com.github.maxtihon.urlshortener.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ShortUrl {

    @Id
    @Column(name = "token")
    private final String token;
    @Column(name = "original_url")
    private final String originalUrl;
    @Column(name = "created_at")
    private final OffsetDateTime createdAt;
    @Column(name = "expired_at")
    private OffsetDateTime expiredAt;
    @Column(name = "redirects")
    private Long redirects = 0L;

    public ShortUrl(String token, String originalUrl, OffsetDateTime createdAt) {
        this.token = token;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        if (this.expiredAt == null) {
            return false;
        }

        return OffsetDateTime.now().isBefore(expiredAt);
    }

    public void increaseRedirects() {
        redirects++;
    }

    public void setExpirationDate(OffsetDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getToken() {
        return token;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getExpiredAt() {
        return expiredAt;
    }

    public Long getRedirects() {
        return redirects;
    }
}
