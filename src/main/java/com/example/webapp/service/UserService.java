package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.model.User;

import java.util.UUID;

public interface UserService {

    User create(UserDto userDto) throws UserException;

    UserDto findById(UUID id) throws UserException;

    User update(UserDto userDto) throws UserException;

    void delete(UUID id) throws UserException;
}

