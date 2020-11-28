package com.example.webapp.dto;

import com.example.webapp.model.User;
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
    private String building;
    private String street;
    private String city;
    private String postNumber;
    private String country;
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
