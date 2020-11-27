package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
public class PostcardController {

    //todo:
    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
    }

    @PostMapping(path = "/{id}")
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto, @PathVariable("id") UUID id) {
        return postcardService.createPostcard(postcardDto, id);
    }

    @PostMapping(path = "/list/{id}")
    public List<Postcard> createListPostcards(@RequestBody List<PostcardDto> postcardList, @PathVariable("id") UUID id) {
        return postcardService.createListPostcards(postcardList, id);
    }

    @GetMapping(path = "/{user_id}")
    public PostcardDto getPostcardById(@PathVariable("user_id") UUID id) {
        return PostcardUtil.map(postcardService.findByPostcardId(id), PostcardDto.class);
    }

    @PutMapping(path = "/{user_id}/{id}")
    public Postcard updatePostcard(@PathVariable("user_id") UUID user_id,
                                   @PathVariable("id") UUID id,
                                   @RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(user_id, id, postcardDetails);
    }

    @PostMapping(path = "/delete/{pid}")
    public void deletePostcard(@PathVariable("pid") UUID id) {
        postcardService.delete(postcardService.findByPostcardId(id));
    }
}