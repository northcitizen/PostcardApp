package com.example.webapp.dto;

import java.util.UUID;

public class PostcardDto {

    private String postNumber;
    private String country;
    private String name;
    private String receiveDate;
    private String sendDate;
    private String distance;

    public PostcardDto(String postNumber, String country, String name, String receiveDate, String sendDate, String distance) {
        this.postNumber = postNumber;
        this.country = country;
        this.name = name;
        this.receiveDate = receiveDate;
        this.sendDate = sendDate;
        this.distance = distance;
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

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
