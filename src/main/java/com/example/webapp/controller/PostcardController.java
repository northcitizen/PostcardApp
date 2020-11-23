package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
public class PostcardController {

    //todo:
    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);

    private final PostcardService postcardService;
    private final UserService userService;

    @Autowired
    public PostcardController(PostcardService postcardService, UserService userService) {
        this.postcardService = postcardService;
        this.userService = userService;
    }


    @GetMapping
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
    }

    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        return postcardService.save(postcardService.createPostcard(postcardDto));
    }


    @GetMapping(path = "/{id}")
    public Optional<Postcard> getPostcardById(@PathVariable("id") UUID id) {
        return postcardService.findByPostcardId(id);
    }

    @PutMapping(path = "/{id}")
    public Postcard updatePostcard(@PathVariable("id") UUID id,
                                   @RequestBody PostcardDto postcardDetails) {
        PostcardDto postcard = PostcardUtil.map(postcardService.findByPostcardId(id), PostcardDto.class);
        postcard.setId(id);
        postcard.setCountry(postcardDetails.getCountry());
        postcard.setSendDate(postcardDetails.getSendDate());
        postcard.setReceiveDate(postcardDetails.getReceiveDate());
        postcard.setPostNumber(postcardDetails.getPostNumber());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setName(postcardDetails.getName());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setDescription(postcardDetails.getDescription());
        postcard.setStatus(postcardDetails.getStatus());
        final Postcard postcard2 = PostcardUtil.DtoToPostcard(postcard);
        return postcardService.save(postcard2);
    }
}