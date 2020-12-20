package com.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
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
    @JsonProperty(required = true)
    private boolean status;
    @JsonProperty(required = true, access = JsonProperty.Access.WRITE_ONLY)
    private UUID userId;

}
