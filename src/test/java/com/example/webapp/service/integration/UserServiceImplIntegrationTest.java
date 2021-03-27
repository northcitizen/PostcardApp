package com.example.webapp.service.integration;

import com.example.webapp.exception.user.UserException;
import com.example.webapp.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)


@SpringBootTest
@Tag("IntegrationTest")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"/users_accounts.sql"})
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByIdTest() throws UserException {
        UUID id = UUID.fromString("750204bd-47a4-43eb-8b50-48a56c2f2f5a");
        Assertions.assertNotNull(userService.findById(id));
    }
}
