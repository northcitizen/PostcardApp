package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.PostcardNotSavedException;
import com.example.webapp.exception.UserNotFoundException;
import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    Postcard findByPostNumber(String postNumber);

    List<PostcardDto> findAll();

    Postcard save(Postcard postcard);

    void delete(UUID id);

    PostcardDto findByPostcardById(UUID id);

    Postcard createPostcard(PostcardDto postcardDto) throws UserNotFoundException, PostcardNotSavedException;

    Postcard updatePostcard(UUID id, PostcardDto postcardDto);

    List<Postcard> createPostcardList(List<PostcardDto> postcardList);
}
