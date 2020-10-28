package com.example.webapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Postcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "post code")
    private String postNumber;
    @NotBlank(message = "country")
    private String country;
    private String description;
    private long distance;
   // private enum conditionValue {bad, good, exellent};
    @DateTimeFormat
    private Date dateOfSent;
    @DateTimeFormat
    private Date dateOfRecieve;

    public Postcard(){
    /*it's very important to create that constructor!*/
    }

    public Postcard(String postNumber, String country) {
        this.postNumber = postNumber;
        this.country = country;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Postcard{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}