package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostcardServiceInterfaceImplTest implements PostcardServiceInterface {
    @Override
    public Postcard findByPostNumber(String postNumber) {
        return null;
    }

    @Override
    public List<Postcard> findAll() {
        return null;
    }

    @Override
    public Postcard save(Postcard postcard) {
        return null;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public Optional<Postcard> findByPostcardId(UUID id) {
        return Optional.empty();
    }

}
