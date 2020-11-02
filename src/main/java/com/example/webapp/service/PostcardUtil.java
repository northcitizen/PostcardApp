package com.example.webapp.service;
import com.example.webapp.model.Postcard;
import com.example.webapp.dto.PostcardDto;
import org.springframework.stereotype.Service;

public class PostcardUtil {
    public static PostcardDto postcardToDto (Postcard postcard){
        return new PostcardDto(postcard.getId(), postcard.getPostNumber(),
                postcard.getCountry(), postcard.getName());
    }
}
