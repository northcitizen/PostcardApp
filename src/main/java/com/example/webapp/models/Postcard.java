package com.example.webapp.models;

import org.springframework.context.annotation.EnableMBeanExport;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Postcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String postNumber;
    private String country;

    public Postcard(){
        /*it's very important to create that constructor!*/
    }

    public Postcard(String postNumber, String country) {
        this.postNumber = postNumber;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Postcard{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}