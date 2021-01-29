package com.example.webapp.service.integration;

import com.example.webapp.exception.user.UserException;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.unit.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"/users_accounts.sql"})
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void findByIdTest() throws UserException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        Assertions.assertThrows(UserException.class, (Executable) userService.findById(id));
    }
}
