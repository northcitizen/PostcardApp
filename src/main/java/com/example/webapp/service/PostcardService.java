package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardConvertingException;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    // вспоминаем о правиле: throws 1 exception, скрываем детали реализации

    List<PostcardDto> findAll() throws PostcardNotFoundException, PostcardException;

    void delete(UUID id) throws PostcardNotFoundException, PostcardException;

    PostcardDto findByPostcardById(UUID id) throws PostcardConvertingException, PostcardNotFoundException, PostcardException;

    Postcard createPostcard(PostcardDto postcardDto) throws UserNotFoundException, PostcardConvertingException, PostcardException;

    Postcard updatePostcard(PostcardDto postcardDto) throws UserNotFoundException, PostcardConvertingException, PostcardNotFoundException, PostcardException;

    List<Postcard> createPostcardList(List<PostcardDto> postcardList) throws UserNotFoundException, PostcardConvertingException, PostcardNotFoundException, PostcardException;
}
