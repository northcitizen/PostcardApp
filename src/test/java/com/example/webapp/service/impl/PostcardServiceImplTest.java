package com.example.webapp.service.impl;

import com.example.webapp.repository.PostcardRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostcardServiceImplTest {
    @Autowired
    private PostcardServiceImpl postcardService;

    @MockBean
    private PostcardRepository postcardRepository;

}
