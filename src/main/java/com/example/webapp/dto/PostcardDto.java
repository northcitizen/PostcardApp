package com.example.webapp.dto;

 public class PostcardDto {
        private Long id;
        private String postNumber;
        private String country;
        private String description;
        private Long distance;
        private String conditionValue;
        private String dateOfSend;
        private String dateOfReceive;

        public PostcardDto(Long id, String postNumber, String country,
                           String description, Long distance, String conditionValue,
                           String dateOfSend, String dateOfReceive) {
            this.id = id;
            this.postNumber = postNumber;
            this.country = country;
            this.description = description;
            this.distance = distance;
            this.conditionValue = conditionValue;
            this.dateOfSend = dateOfSend;
            this.dateOfReceive = dateOfReceive;
        }

     public void setId(Long id) {
            this.id = id;
        }

        public void setPostNumber(String postNumber) {
            this.postNumber = postNumber;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDistance(Long distance) {
            this.distance = distance;
        }

        public void setConditionValue(String conditionValue){
            this.conditionValue = conditionValue;
        }

        public void setDateOfSend(String dateOfSend) {
            this.dateOfSend = dateOfSend;
        }

        public void setDateOfReceive(String dateOfReceive) {
            this.dateOfReceive = dateOfReceive;
        }
 }
