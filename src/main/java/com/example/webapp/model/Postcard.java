package com.example.webapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table()
public class Postcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;
    private String postNumber;
    private String country;
    private String description;
    private Long   distance;
    private String conditionValue;
    private String dateOfSend;
    private String dateOfReceive;


    public Postcard(){
    /*it's very important to create that constructor!*/
    }

    public Postcard(@NotNull String postNumber, @NotNull String country, @NotNull String description,
                    @NotNull long distance, @NotNull String dateOfSent, @NotNull String dateOfRecieve) {
        this.postNumber = postNumber;
        this.country = country;
        this.description = description;
        this.distance = distance;
        this.dateOfSend = dateOfSent;
        this.dateOfReceive = dateOfRecieve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(String dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public String getDateOfReceive() {
        return dateOfReceive;
    }

    public void setDateOfReceive(String dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    @Override
    public String toString() {
        return "Postcard{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}