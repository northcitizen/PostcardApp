package com.example.webapp.service.impl;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserConvertingException;
import com.example.webapp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void createUserTest() throws UserConvertingException {
        User user = userService.createUser(UserDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .build());
        Assertions.assertNotNull(user);

//TODO jUnit+SpringBoot посмотреть проект
//       assertTrue();
//       assertThat(user, is(notNullValue()));
//    private void assertThat(User user, Matcher<Object> objectMatcher) {
//    }
    }
}
