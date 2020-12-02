package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostcardDto> postcardList() {
        logger.debug("get postcard list request");
        try {
            return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
        } catch (Exception e) {
            logger.debug("mapping from postcard to postcardDto failed", e);
        }
        return null;
    }

    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        logger.debug("://" + postcardDto.toString());
        return postcardService.createPostcard(postcardDto);
    }

    @PostMapping(path = "/batch")
    public List<Postcard> createListPostcards(@RequestBody List<PostcardDto> postcardList) {
        return postcardService.createListPostcards(postcardList);
    }

    @GetMapping(path = "/users/{id}")/*postcard id! not user*/
    public PostcardDto getPostcardByUser(@PathVariable("id") UUID id) {/*id can be null*/
        return PostcardUtil.map(postcardService.findByPostcardId(id), PostcardDto.class);
    }

    @PutMapping(path = "/{id}")
    public Postcard updatePostcard(@PathVariable("id") UUID id,
                                   @RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(id, postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostcard(@PathVariable("id") UUID id) {
        postcardService.delete(postcardService.findByPostcardId(id));
    }
}