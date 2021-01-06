package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createTest() throws UserException {
        User expectedUser = User.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        UserDto userDto = UserDto.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        User actualUser = userService.create(userDto);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void findByIdTest() throws UserConvertingException, UserException {
        User user = User.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        UserDto expectedUser = UserDto.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        UserDto actualUser = userService.findById(any());
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void updateTest() throws UserException {
        User user = User.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        User expectedUser = User.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        UserDto userDto = UserDto.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        User actualUser = userService.update(userDto);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void deleteTest() throws UserException {
        User user = mock(User.class);
        when(userRepository.findUserById(any())).thenReturn(user);
        userService.delete(user.getId());
        verify(userRepository, times(1)).delete(user);
    }
}
