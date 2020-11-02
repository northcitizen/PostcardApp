package com.example.webapp.dto;

import java.util.UUID;

public class PostcardDto {
    private UUID id;
    private String postNumber;
    private String country;
    private String name;


    public PostcardDto(UUID id, String postNumber, String country, String name) {
        //this.id = id;
        this.postNumber = postNumber;
        this.country = country;
        this.name = name;
        //todo
        // String receiveDate
        //   distance
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }
}
