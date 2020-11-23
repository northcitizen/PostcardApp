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
    private PostcardStatus status;
    private LocalDateTime receiveDate;
    private LocalDateTime sendDate;
    private User user;

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

    public PostcardBuilder setStatus(PostcardStatus status) {
        this.status = status;
        return this;
    }

    public PostcardBuilder setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
        return this;
    }

    public PostcardBuilder setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public PostcardBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Postcard getPostcard() {
        return new Postcard(id, postNumber, country, name,
                description, distance, status,
                receiveDate, sendDate, user);
    }
}