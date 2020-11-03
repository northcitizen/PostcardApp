package com.example.webapp.controller;

import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class PostcardController {


    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
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
        List<Postcard> postcards = postcardService.findAll();
        model.put("postcards", postcards);
        return "main";
    }

    @PostMapping
    public String addPostcard(@RequestParam String postNumber, @RequestParam String country,
                              @RequestParam String name, @RequestParam String description,
                              @RequestParam Long distance, @RequestParam String conditionValue,
                              @RequestParam String dateOfSend,
                              @RequestParam String dateOfReceive,
                              Map<String, Object> model) {

        Postcard postcard = postcardService.add(postNumber, country, name, description,
                distance, conditionValue, dateOfSend, dateOfReceive);
        postcardService.save(postcard);
        showPostcardList(model);
        return "main";
    }

    @PostMapping("getDistanceYear")
    public String getDistanceYear(@RequestParam String year, Map<String, Object> model) {

        List<Postcard> postcards = postcardService.findByYear(Integer.parseInt(year));
        postcardService.getDistance(postcards, model);
        return "main";
    }


    @PostMapping("filterByDate")
    public String filterByDate(@RequestParam String dateFrom, @RequestParam String dateTo, Map<String, Object> model) throws ParseException {

        List<Postcard> postcards = postcardService.findAll();
        postcardService.filter(dateFrom, dateTo, postcards, model);
        return "main";
    }
}