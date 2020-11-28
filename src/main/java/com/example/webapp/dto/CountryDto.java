package com.example.webapp.dto;

import com.example.webapp.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CountryDto {

    private UUID cid;
    @JsonProperty(required = true)
    private String country;
    @JsonProperty(required = true)
    private Long cardsNumber;
    @JsonProperty(required = true)
    private Long totalDistance;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public static class Builder {
        private final UUID cid;
        private final String country;
        private final Long cardsNumber;
        private final Long totalDistance;
        private final User user;

        public Builder(UUID cid, String country, Long cardsNumber, Long totalDistance, User user) {
            this.cid = cid;
            this.country = country;
            this.cardsNumber = cardsNumber;
            this.totalDistance = totalDistance;
            this.user = user;
        }

        public CountryDto build() {
            return new CountryDto(this);
        }
    }

    private CountryDto(Builder builder) {
        cid = builder.cid;
        country = builder.country;
        cardsNumber = builder.cardsNumber;
        totalDistance = builder.totalDistance;
        user = builder.user;
    }
}
