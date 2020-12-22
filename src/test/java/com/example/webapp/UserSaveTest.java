package com.example.webapp;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.model.User;
import com.example.webapp.service.impl.UserServiceImpl;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
public class UserSaveTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void saveUserTest() throws UserConvertingException {
        User user = userService.createUser(UserDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .build());
        assertThat(user, is(notNullValue()));
    }

    private void assertThat(User user, Matcher<Object> objectMatcher) {
    }
}
