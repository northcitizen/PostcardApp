package com.example.webapp.controllers;

import com.example.webapp.models.Postcard;
import com.example.webapp.repository.PostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PostcardController {

    @Autowired
    private PostcardRepository postcardRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping()
    public String main(Map<String, Object> model){
        Iterable<Postcard> postcards = postcardRepository.findAll();
        model.put("postcards", postcards);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String postNumber, @RequestParam String country,
                      Map<String, Object> model){

        Postcard postcard = new Postcard(postNumber, country);
        postcardRepository.save(postcard);//save to repo

        Iterable<Postcard> postcards = postcardRepository.findAll(); //took from repo
        model.put("postcards", postcards);//put for view
        return "main";
    }
}