package com.example.webapp.service;

import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Postcard> findById(Long id) {

        return postcardRepository.findById(id);
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

    public Optional<Postcard> findByCountry(String country) {
        return postcardRepository.findByCountry(country);
    }

    public Optional<Postcard> findByPostNumber(String postNumber) {
        return postcardRepository.findByPostNumber(postNumber);
    }

    public List<Postcard> findByYear(int year) {
        return postcardRepository.findByYear(year);
    }

    public void filter(String startDate, String endDate, List<Postcard> postcards, Map<String, Object> model) {
        LocalDateTime sDate = LocalDate.parse(startDate, dtf).atStartOfDay();
        LocalDateTime eDate = LocalDate.parse(endDate, dtf).atStartOfDay();
        if (!CollectionUtils.isEmpty(postcards)) {
            for (Postcard card : postcards) {
                LocalDateTime date = card.getDateOfReceive();
                if (date.isAfter(sDate) && date.isBefore(eDate)) {
                    model.put("cards", card);
                }
            }
        }
    }

    public Postcard add(String postNumber, String country, String name, String description,
                        Long distance, String conditionValue, String dateOfSend,
                        String dateOfReceive) {
        LocalDateTime receiveDate = LocalDate.parse(dateOfReceive, dtf).atStartOfDay();
        LocalDateTime sendDate = LocalDate.parse(dateOfSend, dtf).atStartOfDay();
        Postcard postcard = new Postcard(postNumber, country, name, description,
                distance, conditionValue, sendDate, receiveDate);
        return postcard;
    }

    public Long getDistance(List<Postcard> postcards, Map<String, Object> model) {
        Long distance = 0L;
        if (!CollectionUtils.isEmpty(postcards)) {// rewrite it for lambda
            for (Postcard card : postcards) {
                distance += card.getDistance();
            }
            model.put("distance", distance);
        } //else throw exception here
        return distance;
    }
}
