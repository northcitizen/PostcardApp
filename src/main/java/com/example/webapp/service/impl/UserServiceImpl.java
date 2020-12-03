package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.*;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return userRepository.save(
                addUser(userDto.getFirstName(), // можно скрыть .getFirstName() внутри addUser
                        userDto.getLastName(), // можно скрыть .getLastName() внутри addUser
                        userDto.getEmail(), // можно скрыть .getEmail() внутри addUser
                        PostcardUtil.mapAll(userDto.getPostcards(), Postcard.class),
                        PostcardUtil.mapAll(userDto.getAddresses(), Address.class),
                        PostcardUtil.mapAll(userDto.getCountries(), Country.class)));
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

    //    @Override
    private User addUser(String firstName, String lastName,
                         String email, List<Postcard> postcards,
                         List<Address> addresses, List<Country> countries) {
        return new UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPostcards(postcards)
                .setAddresses(addresses)
                .setCountries(countries)
                .getUser();
    }


}
