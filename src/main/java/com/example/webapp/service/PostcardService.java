package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardStatus;
import com.example.webapp.model.User;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    Postcard findByPostNumber(String postNumber);

    List<Postcard> findAll();

    Postcard save(Postcard postcard);

    void delete(Postcard postcard);

    Postcard findByPostcardId(UUID id);

    Postcard add(String postNumber, String country, String name, String description,
                 Long distance, PostcardStatus status, String sendDate,
                 String receiveDate, User user);

    Postcard createPostcard(PostcardDto postcardDto, UUID id);

    Postcard updatePostcard(UUID user_id, UUID id, PostcardDto postcardDto);
}
