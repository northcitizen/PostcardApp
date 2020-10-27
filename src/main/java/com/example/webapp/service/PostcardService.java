package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostcardService {

    private final PostcardRepository postcardRepository;
    @Autowired
    public PostcardService(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    public Postcard save(Postcard postcard){
        return postcardRepository.save(postcard);
    }

    public Optional<Postcard> findById(Long id){
        return postcardRepository.findById(id);
    }

    public Iterable<Postcard> findAll(){
        return postcardRepository.findAll();
    }
}
