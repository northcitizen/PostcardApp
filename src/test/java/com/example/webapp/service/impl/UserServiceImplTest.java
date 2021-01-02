package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUserTest() throws UserConvertingException, UserException {
        User user = User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        when(userRepository.save(user)).thenReturn(user);
        UserDto userDto = userService.userToDTO(user);
        Assertions.assertEquals(user, userService.createUser(userDto));
    }

    @Test
    public void deleteUserTest() throws UserNotFoundException, UserException {
        UUID id = UUID.fromString("2b8892dc-4e99-4993-a8f3-ffdfd15b7d1c");
        User user = User.builder()
                .id(id)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .build();
        when(userRepository.findUserById(id)).thenReturn(user);
        userService.delete(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void findUserByIdTest() throws UserNotFoundException, UserConvertingException, UserException {
        UUID id = UUID.fromString("2b8892dc-4e99-4993-a8f3-ffdfd15b7d1c");
        when(userRepository.findUserById(id)).thenReturn(User.builder()
                .id(id)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .build());
        Assertions.assertEquals(id, userService.findUserById(id).getId());
    }

    @Test
    public void updateUserTest() throws UserConvertingException, UserNotFoundException, UserException {
        UUID id = UUID.fromString("2b8892dc-4e99-4993-a8f3-ffdfd15b7d1c");
        User user = User.builder()
                .id(id)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        when(userRepository.save(user)).thenReturn(user);
        UserDto userDto = userService.userToDTO(user);
        when(userRepository.findUserById(id)).thenReturn(user);
        Assertions.assertEquals(user, userService.updateUser(userDto));
    }
}
