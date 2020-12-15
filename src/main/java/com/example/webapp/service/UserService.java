package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;

import java.util.UUID;


public interface UserService {

    void delete(UUID id);

    User save(UserDto userDto);

    UserDto findUserById(UUID id);

//    User addUser(String firstName, String lastName,
//                 String email, List<Postcard> postcards,
//                 List<Address> addresses, List<Country> countries);

    User updateUser(UserDto userDto);
}
