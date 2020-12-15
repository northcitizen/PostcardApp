package com.example.webapp.controller;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return (User) userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") java.util.UUID id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable("id") java.util.UUID id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody UserDto userDto) {
        return (User) userService.updateUser(userDto);
    }
}
