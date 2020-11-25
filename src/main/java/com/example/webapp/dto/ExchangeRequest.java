package com.example.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    List<PostcardDto> postcards;
}
