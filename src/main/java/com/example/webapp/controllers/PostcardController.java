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

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PostcardController {

    private final PostcardRepository postcardRepository;
    @Autowired
    public PostcardController(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }


    @GetMapping
    public String main(Map<String, Object> model){
        List<Postcard> cards = new ArrayList<>();
        Iterable<Postcard> postcards = postcardRepository.findAll();
        postcards.forEach(cards::add);
        model.put("cards", cards);
        return "main";
    }

    @GetMapping("/greeting")
    public String add(Model model){

        Postcard postcard = new Postcard("7777", "russia");
        //postcardRepository.save(postcard);//save to repo

       // Iterable<Postcard> postcards = postcardRepository.findAll(); //took from repo

        //postcards.forEach(cards::add);

        model.addAttribute("cards", postcard);//put for view
        return "greeting";
    }

    @PostMapping("/greeting")
    public String addButton(@ModelAttribute("postcard") Postcard postcard){

        System.out.println(postcard);
        return "main";
    }
}