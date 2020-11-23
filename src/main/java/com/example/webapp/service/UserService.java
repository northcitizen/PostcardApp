package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;

public interface UserService {

    User save(UserDto userDto);

    User addUser(String firstName, String lastName, String email);
}
