package com.example.webapp.service.impl;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostcardServiceImpl implements PostcardService {

    @Autowired
    CacheManager cacheManager;

    final PostcardRepository postcardRepository;

    @Autowired
    public PostcardServiceImpl(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    @Override
    @Cacheable(value = "postcardCache", key = "#id", unless = "#result==null")
    public Postcard findByPostNumber(String postNumber) {
        return postcardRepository.findByPostNumber(postNumber);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public List<Postcard> findAll() {
        return (List<Postcard>) postcardRepository.findAll();
    }

    @Override
    @CacheEvict(value = "postcardCache", allEntries = true)
    public Postcard save(Postcard postcard) {
        return postcardRepository.save(postcard);
    }

    @Override
    public void deleteById(UUID id) {
        postcardRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public Optional<Postcard> findByPostcardId(UUID id) {
        return postcardRepository.findByPostcardId(id);
    }
}
