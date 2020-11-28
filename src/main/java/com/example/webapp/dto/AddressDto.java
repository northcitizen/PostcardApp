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
public class AddressDto {

    private UUID aid;
    @JsonProperty(required = true)
    private String building;
    @JsonProperty(required = true)
    private String street;
    @JsonProperty(required = true)
    private String city;
    @JsonProperty(required = true)
    private String postNumber;
    @JsonProperty(required = true)
    private String country;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public static class Builder {
        private final UUID aid;
        private final String building;
        private final String street;
        private final String city;
        private final String postNumber;
        private final String country;
        private final User user;

        public Builder(UUID aid, String building, String street, String city,
                       String postNumber, String country, User user) {
            this.aid = aid;
            this.building = building;
            this.street = street;
            this.city = city;
            this.postNumber = postNumber;
            this.country = country;
            this.user = user;
        }

        public AddressDto build() {
            return new AddressDto(this);
        }
    }

    private AddressDto(Builder builder) {
        aid = builder.aid;
        building = builder.building;
        street = builder.street;
        city = builder.city;
        postNumber = builder.postNumber;
        country = builder.country;
        user = builder.user;
    }

}
