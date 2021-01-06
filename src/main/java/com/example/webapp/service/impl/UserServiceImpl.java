package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.dto.PostcardDto;
import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.UserService;
import com.example.webapp.service.util.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDto userDto) throws UserException {
        log.debug("creating user with parameter {}", userDto);
        try {
            User user = dtoToUser(userDto);
            return userRepository.save(user);
        } catch (Exception e) {
            String message = "exception while creating user";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    @Override
    public UserDto findById(UUID id) throws UserException, UserConvertingException {
        log.debug("getting user by id {}", id);
        User user;
        try {
            user = userRepository.findUserById(id);
            if (Objects.isNull(user)) {
                log.error("user with id {} not found", id);
                throw new UserNotFoundException(id);
            }
            return userToDTO(user);
        } catch (Exception e) {
            String message = "exception while getting user from db";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    @Override
    @Transactional
    public User update(UserDto userDto) throws UserException {
        log.debug("updating user with parameters {}", userDto);
        if (Objects.isNull(userDto)) {
            log.error("user dto is null");
            throw new UserException("user dto is null");
        }
        try {
            User userById = userRepository.findUserById(userDto.getId());
            if (Objects.isNull(userById)) {
                String message = "user not found";
                log.error(message);
                throw new UserNotFoundException(message);
            }
            return userRepository.save(updateUser(userById, userDto));
        } catch (Exception e) {
            String message = "exception while updating user";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) throws UserException {
        log.debug("delete user with id {}", id);
        try {
            User userById = userRepository.findUserById(id);
            if (Objects.isNull(userById)) {
                log.error("user not found by id {} ...", id);
                throw new UserNotFoundException(id);
            }
            userRepository.delete(userById);
        } catch (Exception e) {
            String message = "exception while deleting user";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    private User addUser(UserDto userDto) throws UserException {
        try {
            return User.builder()
                    .id(userDto.getId())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .postcards(PostcardUtil.mapAll(userDto.getPostcards(), Postcard.class))
                    .addresses(PostcardUtil.mapAll(userDto.getAddresses(), Address.class))
                    .build();
        } catch (Exception e) {
            String message = "error occurred during converting list";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    private UserDto userToDTO(User user) throws UserConvertingException {
        if (Objects.isNull(user)) {
            log.error("user is null");
            throw new UserConvertingException();
        }
        try {
            return UserDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .postcards(PostcardUtil.mapAll(user.getPostcards(), PostcardDto.class))
                    .addresses(PostcardUtil.mapAll(user.getAddresses(), AddressDto.class))
                    .build();
        } catch (Exception e) {
            String message = "error occurred during converting from entity to dto";
            log.error(message, e);
            throw new UserConvertingException(message, e);
        }
    }

    private User dtoToUser(UserDto userDto) throws UserException {
        if (Objects.isNull(userDto)) {
            String message = "user is null";
            log.error(message);
            throw new UserException(message);
        }
        try {
            return addUser(userDto);
        } catch (Exception e) {
            String message = "error occurred during converting from dto to entity";
            log.error(message, e);
            throw new UserException(message, e);
        }
    }

    private User updateUser(User user, UserDto userDto) throws UserException {
        if (Objects.isNull(user) | Objects.isNull(userDto)) {
            String message = "user or user dto is null";
            log.error(message);
            throw new UserException(message);
        }
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
