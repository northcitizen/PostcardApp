package com.example.webapp.service;

import com.example.webapp.model.Postcard;

import java.util.List;

public interface PostcardTestService {
    Postcard findByPostNumber(String postNumber);
    List<Postcard> findAll();
    Postcard save(Postcard postcard);
}
