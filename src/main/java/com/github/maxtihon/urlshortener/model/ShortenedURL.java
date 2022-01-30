package com.github.maxtihon.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "shortened_url")
public class ShortenedURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String token;
    @Column(name = "original_url")
    private String originalURL;
    @Column(name = "created_at")
    private Date createdAt;
    @Column
    private Long redirects;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
