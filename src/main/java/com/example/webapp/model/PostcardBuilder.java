package com.example.webapp.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostcardBuilder {

    private UUID id;
    private String postNumber;
    private String country;
    private String name;
    private String description;
    private Long distance;
    private String conditionValue;
    private LocalDateTime dateOfSend;
    private LocalDateTime dateOfReceive;


    public PostcardBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public PostcardBuilder setPostNumber(String postNumber) {
        this.postNumber = postNumber;
        return this;
    }

    public PostcardBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public PostcardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PostcardBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PostcardBuilder setDistance(Long distance) {
        this.distance = distance;
        return this;
    }

    public PostcardBuilder setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
        return this;
    }

    public PostcardBuilder setDateOfSend(LocalDateTime dateOfSend) {
        this.dateOfSend = dateOfSend;
        return this;
    }

    public PostcardBuilder setDateOfReceive(LocalDateTime dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
        return this;
    }

    public Postcard getPostcard() {
        return new Postcard(id, postNumber, country, name,
                description, distance, conditionValue,
                dateOfSend, dateOfReceive);
    }
}