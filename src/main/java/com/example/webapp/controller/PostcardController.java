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

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // todo interesting code part
//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
//                           Map<String, Object> model) {
//        model.put("name", name);
//        return "greeting";
//    }

    //todo name of methods

    @GetMapping
    public String show(Map<String, Object> model) {
        List<Postcard> postcards = postcardService.findAll();
        model.put("postcards", postcards);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String postNumber, @RequestParam String country,
                      @RequestParam String name, @RequestParam String description,
                      @RequestParam Long distance, @RequestParam String conditionValue,
                      @RequestParam String dateOfSend,
                      @RequestParam String dateOfReceive,
                      Map<String, Object> model) {

        LocalDate receiveDate = dtf.parse(dateOfReceive, LocalDate::from);
        LocalDate sendDate = dtf.parse(dateOfSend, LocalDate::from);
        Postcard postcard = new Postcard(postNumber, country, name,
                description, distance, conditionValue,
                sendDate, receiveDate);

        postcardService.save(postcard);
        show(model);
        return "main";
    }

    @PostMapping("getDistanceYear")
    public String getDistanceYear(@RequestParam String year, Map<String, Object> model) {
        Long distance = 0L;
        List<Postcard> postcards = postcardService.findByYear(Integer.parseInt(year));

        if (!CollectionUtils.isEmpty(postcards)) {// rewrite it for lambda
            for (Postcard card : postcards) {
                distance += card.getDistance();
            }
            model.put("distance", distance);
        } //else throw exception here
        return "main";
    }


    @PostMapping("filterByDate")
    public String filter(@RequestParam String dateFrom, @RequestParam String dateTo, Map<String, Object> model) throws ParseException {

        //todo  DateTimeFormat
        LocalDate sDate = LocalDate.parse(dateFrom);
        LocalDate eDate = LocalDate.parse(dateTo);

        //todo
        List<Postcard> postcards = postcardService.findAll();
        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards) {
                LocalDate date = card.getDateOfReceive();
                if (date.isAfter(sDate) && date.isBefore(eDate)) {
                    model.put("cards", card);
                }
            }
        }
        return "main";
    }
}