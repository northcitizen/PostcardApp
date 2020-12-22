package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
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
    public List<PostcardDto> postcardList() throws PostcardException {
        try {
            return postcardService.findAll();
        } catch (PostcardNotFoundException e) {
            throw new PostcardException("exception while getting all postcards", e);
        }
    }

    @PostMapping
    public Postcard create(@RequestBody PostcardDto postcardDto) throws PostcardException {
        log.debug("create postcard with parameters {}", postcardDto);
        try {
            return postcardService.createPostcard(postcardDto);
        } catch (Exception e) {
            throw new PostcardException("exception while creating postcard", e);
        }
    }

    @PostMapping(path = "/batch")
    public List<Postcard> createList(@RequestBody List<PostcardDto> postcardList) throws PostcardException {
        log.debug("creating list of postcards...");
        try {
            return postcardService.createPostcardList(postcardList);
        } catch (Exception e) {
            throw new PostcardException("exception while creating list of postcards", e);
        }
    }

    @GetMapping(path = "/{id}")
    public PostcardDto getById(@PathVariable("id") UUID id) throws PostcardException {
        try {
            return postcardService.findByPostcardById(id);
        } catch (Exception e) {
            throw new PostcardException("exception while getting postcard with id=\"" + id + "\"", e);
        }
    }

    @PutMapping
    public Postcard update(@RequestBody PostcardDto postcardDetails) throws PostcardException {
        try {
            return postcardService.updatePostcard(postcardDetails);
        } catch (Exception e) {
            throw new PostcardException("exception while updating postcard", e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") UUID id) throws PostcardException {
        try {
            postcardService.delete(id);
        } catch (Exception e) {
            throw new PostcardException("exception while deleting postcard with id=\"" + id + "\"", e);
        }
    }
}