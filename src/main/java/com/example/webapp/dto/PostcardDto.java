package com.example.webapp.dto;

import com.example.webapp.model.PostcardStatus;
import com.example.webapp.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class PostcardDto {

    private UUID id;
    private String postNumber;
    private String country;
    private String name;
    private String description;
    private Long distance;
    private PostcardStatus status;
    private String sendDate;
    private String receiveDate;
    private User user;

    public static class Builder {
        //required parameters
        private final UUID id;
        private final String postNumber;
        private final String country;
        private final String sendDate;
        private final String receiveDate;
        private final User user;
        //default parameters
        private String name = "name not set";
        private String description = "none";
        private Long distance = 0L;
        private PostcardStatus status = PostcardStatus.TRAVELLING;

        public Builder(UUID id, String setPostNumber, String setCountry,
                       String setSendDate, String setReceiveDate, User user) {
            this.id = id;
            this.postNumber = setPostNumber;
            this.country = setCountry;
            this.sendDate = setSendDate;
            this.receiveDate = setReceiveDate;
            this.user = user;
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

        public Builder status(PostcardStatus status) {
            this.status = status;
            return this;
        }

        public PostcardDto build() {
            return new PostcardDto(this);
        }
    }

    private PostcardDto(Builder builder) {
        id = builder.id;
        postNumber = builder.postNumber;
        country = builder.country;
        sendDate = builder.sendDate;
        receiveDate = builder.receiveDate;
        name = builder.name;
        description = builder.description;
        distance = builder.distance;
        status = builder.status;
        user = builder.user;
    }
}
