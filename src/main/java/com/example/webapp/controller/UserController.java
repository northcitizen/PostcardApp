package com.example.webapp.controller;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody UserDto user) throws UserException {
        try {
            return userService.createUser(user);
        } catch (UserConvertingException e) {
            throw new UserException("exception while creating user", e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") UUID id) throws UserException {
        try {
            userService.delete(id);
        } catch (Exception e) {
            throw new UserException("exception while deleting user", e);
        }
    }

    @GetMapping(path = "/{id}")
    public UserDto get(@PathVariable("id") UUID id) throws UserException{
        try {
            return userService.findUserById(id);
        } catch (Exception e) {
            throw new UserException("exception while getting user", e);
        }
    }

    @PutMapping
    public User update(@RequestBody UserDto userDto) throws UserException{
        try {
            return userService.updateUser(userDto);
        } catch (Exception e) {
            throw new UserException("exception while updating user", e);
        }
    }
}
