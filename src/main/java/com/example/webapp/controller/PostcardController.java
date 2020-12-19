package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
@Slf4j
public class PostcardController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostcardDto> postcardList() {
        return postcardService.findAll();
    }

    @PostMapping
    public Postcard create(@RequestBody PostcardDto postcardDto) {
        log.debug("create postcard with parameters {}", postcardDto);
        return postcardService.createPostcard(postcardDto);
    }

    @PostMapping(path = "/batch")
    public List<Postcard> createList(@RequestBody List<PostcardDto> postcardList) {
        return postcardService.createPostcardList(postcardList);
    }

    @GetMapping(path = "/{id}")
    public PostcardDto getById(@PathVariable("id") UUID id) {
        return postcardService.findByPostcardById(id);
    }

    @PutMapping
    public Postcard update(@RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        postcardService.delete(id);
    }
}