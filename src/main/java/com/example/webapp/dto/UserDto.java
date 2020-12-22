package com.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AddressDto> addresses;
}
