package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardServiceInterface;
import com.example.webapp.service.PostcardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
public class PostcardController {

    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);

    private final PostcardServiceInterface postcardServiceInterface;

    @Autowired
    public PostcardController(@Qualifier("postcardServiceInterfaceImpl") PostcardServiceInterface postcardServiceInterface) {
        this.postcardServiceInterface = postcardServiceInterface;
    }

    @GetMapping
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardServiceInterface.findAll(), PostcardDto.class);
    }

    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        return postcardServiceInterface.save(PostcardUtil.DtoToPostcard(postcardDto));
    }

    @GetMapping(path = "/{id}")
    public Optional<Postcard> getPostcardById(@PathVariable("id") UUID id) {
        return postcardServiceInterface.findByPostcardId(id);
    }

    @PutMapping(path = "/{id}")
    public Postcard updatePostcard(@PathVariable("id") UUID id,
                                   @RequestBody PostcardDto postcardDetails) {
        PostcardDto postcard = PostcardUtil.map(postcardServiceInterface.findByPostcardId(id), PostcardDto.class);
        postcard.setId(id);
        postcard.setCountry(postcardDetails.getCountry());
        postcard.setDateOfSend(postcardDetails.getDateOfSend());
        postcard.setDateOfReceive(postcardDetails.getDateOfReceive());
        postcard.setPostNumber(postcardDetails.getPostNumber());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setName(postcardDetails.getName());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setDescription(postcardDetails.getDescription());
        postcard.setConditionValue(postcardDetails.getConditionValue());
        final Postcard postcard2 = PostcardUtil.DtoToPostcard(postcard);
        return postcardServiceInterface.save(postcard2);
    }
}