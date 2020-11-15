package com.example.webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


//todo hibernate validation
@Entity
@Table(name = "postcard")
public class Postcard {

    static final int MAX_COUNTRY_SIZE = 100;
    static final int MIN_COUNTRY_SIZE = 2;
    static final int POST_NUMBER_SIZE = 9;

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    @NotBlank(message = "Field postnumber can not be blank")
    @Size(min = POST_NUMBER_SIZE, max = POST_NUMBER_SIZE)
    private String postNumber;

    @Column
    @NotBlank(message = "Field country can not be blank")
    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE)
    private String country;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @NotNull(message = "Field distance can not be blank")
    private Long distance;

    @Column
    private String conditionValue;

    @Column
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateOfSend;

    @Column
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateOfReceive;

    @Column
    @NotNull(message = "Field year can not be blank")
    private int year;


    public int getYear() {
        return this.year = dateOfReceive.getYear();

    }


    public Postcard() {
        /*it's very important to create that constructor!*/
    }

    public Postcard(@NotBlank(message = "Field postnumber can not be blank")
                    @Size(min = POST_NUMBER_SIZE, max = POST_NUMBER_SIZE) String postNumber,
                    @NotBlank(message = "Field country can not be blank")
                    @Size(min = MIN_COUNTRY_SIZE, max = MAX_COUNTRY_SIZE) String country,
                    String name,
                    String description,
                    @NotNull(message = "Field distance can not be blank") Long distance,
                    String conditionValue,
                    @NotNull LocalDateTime dateOfSend,
                    @NotNull LocalDateTime dateOfReceive) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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