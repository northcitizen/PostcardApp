package com.example.webapp.service;

import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostcardService {

    Postcard findByPostNumber(String postNumber);

    List<Postcard> findAll();

    Postcard save(Postcard postcard);

    void deleteById(UUID uuid);

    Optional<Postcard> findByPostcardId(UUID id);
}
