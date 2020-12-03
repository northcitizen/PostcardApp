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

    private UUID id;
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

    // why not @Builder?
    public static class Builder {
        private final UUID id;
        private final String building;
        private final String street;
        private final String city;
        private final String postNumber;
        private final String country;
        private final User user;

        public Builder(UUID id, String building, String street, String city,
                       String postNumber, String country, User user) {
            this.id = id;
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

    // конструкторы с любыми модификаторами обычно идут после объявления полей класса
    private AddressDto(Builder builder) {
        id = builder.id;
        building = builder.building;
        street = builder.street;
        city = builder.city;
        postNumber = builder.postNumber;
        country = builder.country;
        user = builder.user;
    }

}
