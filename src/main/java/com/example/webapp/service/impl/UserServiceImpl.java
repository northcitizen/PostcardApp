package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.dto.PostcardDto;
import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void delete(UUID id) throws UserNotFoundException {
        User userById = userRepository.findUserById(id);
        if (Objects.isNull(userById)) {
            log.error("user not found by id {} ...", id);
            throw new UserNotFoundException(id);
        }
        userRepository.delete(userById);
    }

    @Override
    public User createUser(UserDto userDto) throws UserConvertingException {
        log.debug("creating user with parameter {}", userDto);
        try {
            return userRepository.save(dtoToUser(userDto));
        } catch (Exception e) {
            log.error("error occurred during converting dto to user", e);
            throw new UserConvertingException();
        }
    }

    @Override
    public UserDto findUserById(UUID id) throws UserNotFoundException {
        User userById = null;
        try {
            userById = userRepository.findUserById(id);
            if (Objects.isNull(userById)) {
                log.error("user not found by id {}", id);
                throw new UserNotFoundException(id);
            }
        } catch (Exception e) {
            log.error("user not found by id", e);
            e.printStackTrace();
        }
        try {
            log.debug("find user by id request...");
            return userToDTO(userById);
        } catch (Exception e) {
            log.error("user not found by id {} ...", id);
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User updateUser(UserDto userDto) throws UserNotFoundException, UserConvertingException {
        if (Objects.isNull(userRepository.findUserById(userDto.getId())))
            throw new UserNotFoundException("user not found in update service...");
        try {
            log.debug("update user service...");
            return userRepository.save(dtoToUser(userDto));
        } catch (Exception e) {
            log.error("error occurred by mapping", e);
            throw new UserConvertingException();
        }
    }

    private User addUser(UserDto userDto) throws UserConvertingException {
        if (Objects.isNull(userDto)) {
            throw new UserConvertingException();
        }
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .postcards(PostcardUtil.mapAll(userDto.getPostcards(), Postcard.class))
                .addresses(PostcardUtil.mapAll(userDto.getAddresses(), Address.class))
                .build();
    }

    private UserDto userToDTO(User user) throws UserConvertingException {
        if (Objects.isNull(user)) {
            log.error("user is null");
            throw new UserConvertingException();
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .postcards(PostcardUtil.mapAll(user.getPostcards(), PostcardDto.class))
                .addresses(PostcardUtil.mapAll(user.getAddresses(), AddressDto.class))
                .build();
    }

    private User dtoToUser(UserDto userDto) throws UserConvertingException {
        if (Objects.isNull(userDto)) {
            log.error("user is null");
            throw new UserConvertingException();
        }
        return addUser(userDto);
    }
}
