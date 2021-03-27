package com.example.webapp.controller;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User create(@RequestBody UserDto user) throws UserException {
        log.debug("creating user with parameters {}", user);
        return userService.create(user);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto get(@PathVariable("id") UUID id) throws UserException {
        log.debug("getting user with id {}", id);
        return userService.findById(id);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public User update(@RequestBody UserDto userDto) throws UserException {
        log.debug("update user with parameters {}", userDto);
        return userService.update(userDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) throws UserException {
        log.debug("deleting user with id {}", id);
        userService.delete(id);
    }
}
