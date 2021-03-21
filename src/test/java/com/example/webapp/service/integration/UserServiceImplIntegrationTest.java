package com.example.webapp.service.integration;

import com.example.webapp.dto.UserDto;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Tag("IntegrationTest")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"/users_accounts.sql"})
public class UserServiceImplIntegrationTest {

    @Autowired
    UserService userService;


    @Test
    public void findByIdTest() throws UserException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        UserDto user = userService.findById(id);
        //Assertions.assertThrows(UserException.class, (Executable) userService.findById(id));
    }
}
