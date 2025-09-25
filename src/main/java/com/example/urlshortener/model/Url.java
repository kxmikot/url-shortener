package com.example.urlshortener.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column(nullable=false)
    private String originalUrl;

    @Column(nullable=false, unique=true)
    private String shortKey;

    private final LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl (String originalUrl) { this.originalUrl = originalUrl; }
    public String getShortKey() { return shortKey; }
    public void setShortKey (String shortKey) { this.shortKey = shortKey; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
