package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.UserNotFoundException;
import com.example.webapp.exception.UserNotUpdatedException;
import com.example.webapp.model.Address;
import com.example.webapp.model.Country;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final CacheManager cacheManager; //TODO применить кэш
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(CacheManager cacheManager, UserRepository userRepository) {
        this.cacheManager = cacheManager;
        this.userRepository = userRepository;
    }

    @Override
    public void delete(UUID id) {
        if (userRepository.findUserById(id) == null)
            throw new UserNotFoundException("postcard not found in update service...");
        userRepository.delete(userRepository.findUserById(id));
    }

    @Override
    public User save(UserDto userDto) {
        return userRepository.save(addUser(userDto));
    }

    @Override
    public UserDto findUserById(UUID id) throws NullPointerException {
        try {
            log.debug("find user by id request...");
            return PostcardUtil.map(userRepository.findUserById(id), UserDto.class);
        } catch (RuntimeException e) {
            log.error("user not found by id...", e);
            throw new UserNotFoundException();
        }
    }

    @Override
    public User updateUser(UserDto userDto) {
        if (userRepository.findUserById(userDto.getId()) == null)
            throw new UserNotFoundException("user not found in update service...");
        try {
            log.debug("update user service...");
            User userUpdate = PostcardUtil.map(userDto, User.class);
            userUpdate.setId(userDto.getId());
            return userRepository.save(userUpdate);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping", e);
            throw new UserNotUpdatedException();
        }
    }

    //@Override забыл почему так private
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
