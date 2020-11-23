package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardStatus;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import com.example.webapp.service.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
public class PostcardController {

    //todo:
    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);

    private UserMapper userMapper;

    private final PostcardService postcardService;
    private final UserRepository userRepository;

    @Autowired
    public PostcardController(PostcardService postcardService, UserRepository userRepository) {
        this.postcardService = postcardService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
    }

    //35424540-3cf9-44fd-bee9-ff4e3031fce5
    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        User user = new User();

        //user - it is parent
        user.setUser_id(postcardDto.getUser().getUser_id());//-
        user.setFirstName("v");
        user.setLastName("b");
        user.setEmail("vb@gmail.com");

        //postcard - it is child
        postcardDto.setStatus(PostcardStatus.TRAVELLING);
        postcardDto.setDescription(postcardDto.getDescription());
        postcardDto.setDistance(postcardDto.getDistance());
        postcardDto.setName(postcardDto.getName());
        postcardDto.setPostNumber(postcardDto.getPostNumber());
        postcardDto.setCountry(postcardDto.getCountry());
        postcardDto.setReceiveDate(postcardDto.getReceiveDate());
        postcardDto.setSendDate(postcardDto.getSendDate());
        postcardDto.setUser(user);

        return postcardService.save(PostcardUtil.DtoToPostcard(postcardDto));
    }


    @GetMapping(path = "/{id}")
    public Optional<Postcard> getPostcardById(@PathVariable("id") UUID id) {
        return postcardService.findByPostcardId(id);
    }

    @PutMapping(path = "/{id}")
    public Postcard updatePostcard(@PathVariable("id") UUID id,
                                   @RequestBody PostcardDto postcardDetails) {
        PostcardDto postcard = PostcardUtil.map(postcardService.findByPostcardId(id), PostcardDto.class);
        postcard.setId(id);
        postcard.setCountry(postcardDetails.getCountry());
        postcard.setSendDate(postcardDetails.getSendDate());
        postcard.setReceiveDate(postcardDetails.getReceiveDate());
        postcard.setPostNumber(postcardDetails.getPostNumber());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setName(postcardDetails.getName());
        postcard.setDistance(postcardDetails.getDistance());
        postcard.setDescription(postcardDetails.getDescription());
        postcard.setStatus(postcardDetails.getStatus());
        final Postcard postcard2 = PostcardUtil.DtoToPostcard(postcard);
        return postcardService.save(postcard2);
    }
}