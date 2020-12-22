package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.User;

import java.util.UUID;


public interface UserService {

    void delete(UUID id) throws UserNotFoundException;

    UserDto findUserById(UUID id) throws UserNotFoundException;

    User updateUser(UserDto userDto) throws UserConvertingException, UserNotFoundException;

    User createUser(UserDto userDto) throws UserConvertingException;
}
