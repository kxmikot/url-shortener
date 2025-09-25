package com.example.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urlshortener.repository.*;
import com.example.urlshortener.model.*;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Url shortenUrl(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortKey(generateShortKey());
        return urlRepository.save(url);
    }

    public Url getOriginalUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
            .orElseThrow(() -> new RuntimeException("URL not found"));
    }   

    private String generateShortKey() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            key.append(chars.charAt(random.nextInt(chars.length())));
        }
        Optional<Url> existing = urlRepository.findByShortKey(key.toString());
        if (existing.isPresent()) {
            return generateShortKey();
        } else {
            return key.toString();
        }
    }
}
