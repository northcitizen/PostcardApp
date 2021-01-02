package com.example.webapp.service.impl;

import com.example.webapp.repository.PostcardRepository;
import org.junit.Test;
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


    @Test
    public void findAllTest() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void findByPostcardByIdTest() {
    }

    @Test
    public void createPostcardTest() {
    }

    @Test
    public void createPostcardListTest() {
    }

    @Test
    public void updatePostcardTest() {
    }
}
