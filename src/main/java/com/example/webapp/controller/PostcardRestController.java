package com.example.webapp.controller;

import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postcards")
public class PostcardRestController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardRestController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    public List<Postcard> postcardList() {
        return postcardService.findAll();
    }

    @GetMapping("/{id}")
    public List<Postcard> getPostcard(@PathVariable("id") final UUID id) {
        return postcardService.findById(id);
    }
//todo : waiting for frontend
//    @PostMapping
//    public void create(@RequestBody Postcard postcard){
//        postcardService.save(postcard);
//    }

    @GetMapping("/distance/{year}")
    public Long getPostcardDistance(@PathVariable("year") final String year) {
        return postcardService.getDistance(year);
    }


}
