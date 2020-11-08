package com.example.webapp.controller;

import com.example.webapp.service.HandMadePostcardService;
import com.example.webapp.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Map;

@Controller
public class PostcardController {


    private final HandMadePostcardService handMadePostcardService;
    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService,
                              HandMadePostcardService handMadePostcardService) {
        this.handMadePostcardService = handMadePostcardService;
        this.postcardService = postcardService;
    }

    // todo interesting code part
//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
//                           Map<String, Object> model) {
//        model.put("name", name);
//        return "greeting";
//    }

    @GetMapping
    public String showPostcardList(Map<String, Object> model) {
        model.put("postcards", postcardService.findAll());
        return "main";
    }

    @PostMapping
    public String addPostcard(@RequestParam String postNumber,
                              @RequestParam String country,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long distance,
                              @RequestParam String conditionValue,
                              @RequestParam String dateOfSend,
                              @RequestParam String dateOfReceive,
                              Map<String, Object> model) {
        postcardService.addPostcard(postNumber,
                country,
                name,
                description,
                distance,
                conditionValue,
                dateOfSend,
                dateOfReceive);
        showPostcardList(model);
        return "main";
    }

    @PostMapping("getDistanceYear")
    public String getDistanceYear(@RequestParam String year, Map<String, Object> model) {
        model.put("distance", postcardService.getDistance(year));
        return "main";
    }


    @PostMapping("filterByDate")
    public String filterByDate(@RequestParam String dateFrom, @RequestParam String dateTo, Map<String, Object> model) throws ParseException {
        model.put("cards", postcardService.filter(dateFrom, dateTo));
        return "main";
    }
}