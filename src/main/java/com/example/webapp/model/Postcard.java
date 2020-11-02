package com.example.webapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class Postcard {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column
    @NotBlank
    private String postNumber;

    @Column
    @NotBlank
    @Size(min = 2, max = 100)
    private String country;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @Size
    private String description;

    @Column
    @NotNull
    private Long distance;

    @Column
    @NotNull
    private String conditionValue;

    @Column
    private @NotNull LocalDateTime dateOfSend;

    @Column
    private @NotNull LocalDateTime dateOfReceive;

    private int year;

    public int getYear() {
        return this.year = dateOfReceive.getYear();
    }


    public Postcard(){
    /*it's very important to create that constructor!*/
    }

    public Postcard(@NotNull String postNumber, @NotNull String country, @NotNull String name,
                    @NotNull String description, @NotNull Long distance, @NotNull String conditionValue,
                    @NotNull LocalDateTime dateOfSend, @NotNull LocalDateTime dateOfReceive) {
        this.postNumber = postNumber;
        this.country = country;
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.conditionValue = conditionValue;
        this.dateOfSend = dateOfSend;
        this.dateOfReceive = dateOfReceive;
        this.year = getYear();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(LocalDateTime dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public LocalDateTime getDateOfReceive() {
        return dateOfReceive;
    }

    public void setDateOfReceive(LocalDateTime dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    @Override
    public String toString() {
        return "Postcard{" +
                "year=" + year +
                '}';
    }
}