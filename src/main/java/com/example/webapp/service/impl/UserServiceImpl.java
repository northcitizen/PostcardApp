package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;
import com.example.webapp.model.UserBuilder;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final CacheManager cacheManager;
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(CacheManager cacheManager, UserRepository userRepository) {
        this.cacheManager = cacheManager;
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserDto userDto) {

        return userRepository.save(
                addUser(userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail()));
    }

    @Override
    public User addUser(String firstName, String lastName, String email) {
        return new UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .getUser();
    }
}
