package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardConvertingException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.postcard.PostcardNotSavedException;
import com.example.webapp.exception.postcard.PostcardNotUpdatedException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    Postcard findByPostNumber(String postNumber);

    List<PostcardDto> findAll() throws PostcardNotFoundException;

    void delete(UUID id) throws PostcardNotFoundException;

    PostcardDto findByPostcardById(UUID id) throws PostcardConvertingException, PostcardNotFoundException;

    Postcard createPostcard(PostcardDto postcardDto) throws UserNotFoundException, PostcardNotSavedException, PostcardConvertingException;

    Postcard updatePostcard(PostcardDto postcardDto) throws PostcardNotSavedException, UserNotFoundException, PostcardConvertingException, PostcardNotFoundException, PostcardNotUpdatedException;

    List<Postcard> createPostcardList(List<PostcardDto> postcardList) throws UserNotFoundException, PostcardConvertingException, PostcardNotSavedException, PostcardNotFoundException;
}
