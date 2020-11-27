package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    Postcard findByPostNumber(String postNumber);

    List<Postcard> findAll();

    Postcard save(Postcard postcard);

    void delete(Postcard postcard);

    Postcard findByPostcardId(UUID id);

    Postcard createPostcard(PostcardDto postcardDto, UUID id);

    Postcard updatePostcard(UUID user_id, UUID id, PostcardDto postcardDto);

    List<Postcard> createListPostcards(List<PostcardDto> postcardList, UUID id);
}
