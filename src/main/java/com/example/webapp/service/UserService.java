package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.Address;
import com.example.webapp.model.Country;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void delete(User user);

    User save(UserDto userDto);

    User findUserById(UUID id);

//    User addUser(String firstName, String lastName,
//                 String email, List<Postcard> postcards,
//                 List<Address> addresses, List<Country> countries);

    User updateUser(UUID id, UserDto userDto);
}
