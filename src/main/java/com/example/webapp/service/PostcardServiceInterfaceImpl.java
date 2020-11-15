package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostcardTestServiceImpl implements PostcardTestService{
    @Override
    public Postcard findByPostNumber(String postNumber) {
        return findByPostNumber(postNumber);
    }

    @Override
    public List<Postcard> findAll() {
        return findAll();
    }

    @Override
    public Postcard save(Postcard postcard) {
        return save(postcard);
    }
}
