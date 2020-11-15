package com.example.webapp.dto;

public class PostcardDto {

    private String postNumber;
    private String country;
    private String name;
    private String description;
    private Long distance;
    private String conditionValue;
    private String dateOfSend;
    private String dateOfReceive;

    public PostcardDto() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public void setDateOfSend(String dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public void setDateOfReceive(String dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getDistance() {
        return distance;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public String getDateOfSend() {
        return dateOfSend;
    }

    public String getDateOfReceive() {
        return dateOfReceive;
    }

    public String getCountry() {
        return country;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public static class Builder {
        //required parameters
        private final String postNumber;
        private final String country;
        private final String dateOfSend;
        private final String dateOfReceive;
        //default parameters
        private String name = "name not set";
        private String description = "none";
        private Long distance = 0L;
        private String conditionValue = "not set";

        public Builder(String setPostNumber, String setCountry,
                       String setSendDate, String setReceiveDate) {
            this.postNumber = setPostNumber;
            this.country = setCountry;
            this.dateOfSend = setSendDate;
            this.dateOfReceive = setReceiveDate;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder distance(Long distance) {
            this.distance = distance;
            return this;
        }

        public Builder conditionValue(String conditionValue) {
            this.conditionValue = conditionValue;
            return this;
        }

        public PostcardDto build() {
            return new PostcardDto(this);
        }
    }

    private PostcardDto(Builder builder) {
        postNumber = builder.postNumber;
        country = builder.country;
        dateOfSend = builder.dateOfSend;
        dateOfReceive = builder.dateOfReceive;
        name = builder.name;
        description = builder.description;
        distance = builder.distance;
        conditionValue = builder.conditionValue;
    }
}
