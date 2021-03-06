package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.model.Postcard;

import java.util.List;
import java.util.UUID;

public interface PostcardService {

    Postcard create(PostcardDto postcardDto) throws PostcardException;

    List<Postcard> createList(List<PostcardDto> postcardList) throws PostcardException;

    PostcardDto findById(UUID id) throws PostcardException;

    List<PostcardDto> findAll() throws PostcardException;

    Postcard update(PostcardDto postcardDto) throws PostcardException;

    void delete(UUID id) throws PostcardException;
}
