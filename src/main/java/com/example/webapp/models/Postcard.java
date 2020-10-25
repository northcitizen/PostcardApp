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
    private int id;
    private String postNumber;
    private String country;

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public Postcard(String postNumber) {
        this.postNumber = postNumber;
    }

    public Postcard(String postNumber, String country) {
        this.postNumber = postNumber;
        this.country = country;
    }

    public Postcard(int id, String postNumber, String country) {
        this.id = id;
        this.postNumber = postNumber;
        this.country = country;
    }

    /*
        / Constructor
        */
    public Postcard() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Postcard{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}