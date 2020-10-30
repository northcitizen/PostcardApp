package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class PostcardController {


    private final PostcardService postcardService;
    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String show(Map<String, Object> model){
        Iterable<Postcard> postcards = postcardService.findAll();
        model.put("postcards", postcards);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String postNumber, @RequestParam String country,
                      @RequestParam String name, @RequestParam String description,
                      @RequestParam Long distance, @RequestParam String conditionValue,
                      @RequestParam String dateOfSend, @RequestParam String dateOfReceive,
                      Map<String, Object> model){

        Postcard postcard = new Postcard(postNumber, country, name,
                                         description, distance, conditionValue,
                                         dateOfSend, dateOfReceive);
        postcardService.save(postcard);
        show(model);

        Optional<Postcard> distances = postcardService.findByDistance(postcard.getDistance());
        model.put("distances", distances);
        return "main";
    }

}