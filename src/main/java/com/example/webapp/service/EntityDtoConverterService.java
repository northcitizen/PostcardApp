package com.example.webapp.service;
import com.example.webapp.model.Postcard;
import com.example.webapp.dto.PostcardDto;

public class EntityDtoConverterService {
    public PostcardDto postcardToDto (Postcard postcard){
        return new PostcardDto(postcard.getId(), postcard.getPostNumber(),
                postcard.getCountry(), postcard.getName());
    }
}
