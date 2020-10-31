package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<Postcard> findByDistance(Long distance){
        return postcardRepository.findByDistance(distance);
    }

    public Optional<Postcard> findByDateOfReceive(String dateOfReceive){
        return postcardRepository.findByDateOfReceive(dateOfReceive);
    }

    public Optional<Postcard> findByCountry(String country){
        return postcardRepository.findByCountry(country);
    }

    public Optional<Postcard> findByPostNumber(String postNumber){
        return postcardRepository.findByPostNumber(postNumber);
    }

    public List<Postcard> findByYear(String year){
        return postcardRepository.findByYear(year);
    }
}
