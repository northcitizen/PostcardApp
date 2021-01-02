package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.User;

import java.util.UUID;


public interface UserService {

    void delete(UUID id) throws UserNotFoundException, UserException;

    UserDto findUserById(UUID id) throws UserNotFoundException, UserConvertingException, UserException;

    User updateUser(UserDto userDto) throws UserConvertingException, UserNotFoundException, UserException;

    User createUser(UserDto userDto) throws UserConvertingException, UserException;

    UserDto userToDTO(User user) throws UserConvertingException;

    User dtoToUser(UserDto userDto) throws UserConvertingException;
}
