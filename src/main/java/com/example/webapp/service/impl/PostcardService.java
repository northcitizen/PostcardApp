package com.example.webapp.service.impl;

import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardBuilder;
import com.example.webapp.model.PostcardStatus;
import com.example.webapp.repository.PostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostcardService {

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final PostcardRepository postcardRepository;

    @Autowired
    public PostcardService(PostcardRepository postcardRepository) {
        this.postcardRepository = postcardRepository;
    }

    public Postcard save(Postcard postcard) {
        return postcardRepository.save(postcard);
    }

    public Optional<Postcard> findById(UUID id) {
        return postcardRepository.findById(id);
    }

    public List<Postcard> findAll() {
        return (List<Postcard>) postcardRepository.findAll();
    }

    public Optional<Postcard> findByDistance(Long distance) {
        return postcardRepository.findByDistance(distance);
    }

    public List<Postcard> findByCountry(String country) {
        return postcardRepository.findByCountry(country);
    }

    public Postcard findByPostNumber(String postNumber) {
        return postcardRepository.findByPostNumber(postNumber);
    }


    public List<Postcard> filter(String startDate, String endDate) {
        LocalDateTime firstDate = LocalDate.parse(startDate, dtf).atStartOfDay();
        LocalDateTime secondDate = LocalDate.parse(endDate, dtf).atStartOfDay();

        List<Postcard> postcards = findAll();
        List<Postcard> newCards = new ArrayList<>();
        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards) {
                LocalDateTime date = card.getReceiveDate();
                if (date.isAfter(firstDate) && date.isBefore(secondDate)) {
                    newCards.add(card);
                }
            }
        }
        return newCards;
    }

    public Postcard add(String postNumber, String country, String name, String description,
                        Long distance, PostcardStatus status, String sendDate,
                        String receiveDate) {
        LocalDateTime receiveDateParse = LocalDate.parse(receiveDate, dtf).atStartOfDay();
        LocalDateTime sendDateParse = LocalDate.parse(sendDate, dtf).atStartOfDay();
        return new PostcardBuilder()
                .setPostNumber(postNumber)
                .setCountry(country)
                .setName(name)
                .setDescription(description)
                .setDistance(distance)
                .setStatus(status)
                .setReceiveDate(sendDateParse)
                .setSendDate(receiveDateParse)
                .getPostcard();
    }

//    public Long getDistance(String year) {
//        Long distance = 0L;
//
//        List<Postcard> postcards = findByYear(Integer.parseInt(year));
//
//        if (!CollectionUtils.isEmpty(postcards)) {
//            for (Postcard card : postcards)
//                distance += card.getDistance();
//        }
//        return distance;
//    }

    public void addPostcard(String postNumber, String country, String name, String description,
                            Long distance, PostcardStatus status, String sendDate,
                            String receiveDate) {
        Postcard postcard = add(postNumber, country, name, description,
                distance, status, sendDate,
                receiveDate);
        save(postcard);
    }

}
