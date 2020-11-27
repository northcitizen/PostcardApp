package com.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {
    private UUID id;
    @JsonProperty(required = true)
    private String firstName;
    @JsonProperty(required = true)
    private String lastName;
    @JsonProperty(required = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PostcardDto> postcards;

    public static class Builder {
        private final UUID id;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final List<PostcardDto> postcards;

        public Builder(UUID id, String firstName, String lastName,
                       String email, List<PostcardDto> postcards) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.postcards = postcards;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }

    private UserDto(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        postcards = builder.postcards;
    }
}