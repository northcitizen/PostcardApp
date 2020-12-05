package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.Address;
import com.example.webapp.model.Country;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final CacheManager cacheManager; // не используется
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(CacheManager cacheManager, UserRepository userRepository) {
        this.cacheManager = cacheManager;
        this.userRepository = userRepository;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User save(UserDto userDto) {
        return userRepository.save(addUser(userDto));
    }

    @Override
    public User findUserById(UUID id) throws NullPointerException {
        return userRepository.findUserById(id);
    }

    @Override
    public User updateUser(UUID id, UserDto userDto) {
        User userUpdate = PostcardUtil.map(userDto, User.class);
        userUpdate.setId(id);
        return userRepository.save(userUpdate);
    }

    //@Override забыл почему так
    private User addUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .postcards(PostcardUtil.mapAll(userDto.getPostcards(), Postcard.class))
                .addresses(PostcardUtil.mapAll(userDto.getAddresses(), Address.class))
                .countries(PostcardUtil.mapAll(userDto.getCountries(), Country.class))
                .build();
    }
}
