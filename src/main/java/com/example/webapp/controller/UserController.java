package com.example.webapp.controller;

import com.example.webapp.dto.UserDto;
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
    public User createUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable("id") UUID id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
