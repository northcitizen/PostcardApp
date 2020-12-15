package com.example.webapp.dto;

import com.example.webapp.model.PostcardStatus;
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
public class PostcardDto {

    private java.util.UUID id;
    @JsonProperty(required = true)
    private String postNumber;
    @JsonProperty(required = true)
    private String country;
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String description;
    @JsonProperty(required = true)
    private Long distance;
    @JsonProperty(required = true)
    private PostcardStatus status;
    @JsonProperty(required = true)
    private String sendDate;
    @JsonProperty(required = true)
    private String receiveDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID userId;
}
