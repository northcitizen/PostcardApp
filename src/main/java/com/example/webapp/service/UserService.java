package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;

import java.util.UUID;


public interface UserService {

    void delete(UUID id);

    UserDto findUserById(UUID id);

    User updateUser(UserDto userDto);

    User createUser(UserDto userDto);
}
