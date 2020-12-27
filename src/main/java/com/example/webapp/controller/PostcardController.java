package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardConvertingException;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
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
    public List<PostcardDto> postcardList() throws PostcardException, PostcardNotFoundException {
        log.debug("get postcards list requests...");
        return postcardService.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Postcard create(@RequestBody PostcardDto postcardDto) throws PostcardException, UserNotFoundException, PostcardConvertingException {
        log.debug("create postcard with parameters {}", postcardDto);
        return postcardService.createPostcard(postcardDto);
    }

    @PostMapping(path = "/batch")
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<Postcard> createList(@RequestBody List<PostcardDto> postcardList) throws PostcardException, UserNotFoundException, PostcardConvertingException, PostcardNotFoundException {
        log.debug("creating list of postcards...");
        return postcardService.createPostcardList(postcardList);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PostcardDto getById(@PathVariable("id") UUID id) throws PostcardException, PostcardNotFoundException, PostcardConvertingException {
        log.debug("getting postcard by id {}", id);
        return postcardService.findByPostcardById(id);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Postcard update(@RequestBody PostcardDto postcardDetails) throws PostcardException, PostcardNotFoundException, PostcardConvertingException, UserNotFoundException {
        log.debug("updating postcard with parameters {}", postcardDetails);
        return postcardService.updatePostcard(postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) throws PostcardException, PostcardNotFoundException {
        log.debug("deleting postcard");
        postcardService.delete(id);
    }
}