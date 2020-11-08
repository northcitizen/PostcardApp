package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardBuilder;
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

    public List<Postcard> findAllById(Long id) {
        return postcardRepository.findAllById(id);
    }

    public void save(Postcard postcard) {
        postcardRepository.save(postcard);
    }

    public List<Postcard> findById(UUID id) {

        return (List<Postcard>) postcardRepository.findById(id);
    }

    public List<Postcard> findAll() {
        return (List<Postcard>) postcardRepository.findAll();
    }

    public Optional<Postcard> findByDistance(Long distance) {
        return postcardRepository.findByDistance(distance);
    }

    public Optional<Postcard> findByDateOfReceive(String dateOfReceive) {
        return postcardRepository.findByDateOfReceive(dateOfReceive);
    }

    public List<Postcard> findByCountry(String country) {
        return postcardRepository.findByCountry(country);
    }

    public List<Postcard> findByPostNumber(String postNumber) {
        return postcardRepository.findByPostNumber(postNumber);
    }

    public List<Postcard> findByYear(int year) {
        return postcardRepository.findByYear(year);
    }

    public List<Postcard> filter(String startDate, String endDate) {
        LocalDateTime firstDate = LocalDate.parse(startDate, dtf).atStartOfDay();
        LocalDateTime secondDate = LocalDate.parse(endDate, dtf).atStartOfDay();

        List<Postcard> postcards = findAll();
        List<Postcard> newCards = new ArrayList<>();
        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards) {
                LocalDateTime date = card.getDateOfReceive();
                if (date.isAfter(firstDate) && date.isBefore(secondDate)) {
                    newCards.add(card);
                }
            }
        }
        return newCards;
    }

    public Postcard add(String postNumber, String country, String name, String description,
                        Long distance, String conditionValue, String dateOfSend,
                        String dateOfReceive) {
        LocalDateTime receiveDate = LocalDate.parse(dateOfReceive, dtf).atStartOfDay();
        LocalDateTime sendDate = LocalDate.parse(dateOfSend, dtf).atStartOfDay();
        return new PostcardBuilder()
                .setPostNumber(postNumber)
                .setCountry(country)
                .setName(name)
                .setDescription(description)
                .setDistance(distance)
                .setConditionValue(conditionValue)
                .setDateOfSend(sendDate)
                .setDateOfReceive(receiveDate)
                .getPostcard();
    }

    public Long getDistance(String year) {
        Long distance = 0L;

        List<Postcard> postcards = findByYear(Integer.parseInt(year));

        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards)
                distance += card.getDistance();
        }
        return distance;
    }

    public void addPostcard(String postNumber, String country, String name, String description,
                            Long distance, String conditionValue, String dateOfSend,
                            String dateOfReceive) {
        Postcard postcard = add(postNumber, country, name, description,
                distance, conditionValue, dateOfSend,
                dateOfReceive);
        save(postcard);
    }

}
