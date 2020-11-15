package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardServiceInterface;
import com.example.webapp.service.PostcardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PostcardRestController {

    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);

    private final PostcardServiceInterface postcardServiceInterface;

    @Autowired
    public PostcardRestController(@Qualifier("postcardServiceInterfaceImpl") PostcardServiceInterface postcardServiceInterface) {
        this.postcardServiceInterface = postcardServiceInterface;
    }

    @GetMapping(path = "/postcards")
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardServiceInterface.findAll(), PostcardDto.class);
    }

    @PostMapping(path = "/postcard")
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        logger.debug("createPostcard method of restController is working");
        return postcardServiceInterface.save(PostcardUtil.DtoToPostcard(postcardDto));
    }

    @GetMapping("/postcard/{postNumber}")
    public PostcardDto getPostcard(@PathVariable("postNumber") String postNumber) {
        return PostcardUtil.map(postcardServiceInterface.findByPostNumber(postNumber), PostcardDto.class);
    }
}
//todo: need to fix. creates new entity instead of update it
//    @PutMapping("/postcards/{postNumber}")
//    public Postcard updatePostcard(@PathVariable("postNumber") String postNumber,
//                                   @RequestBody PostcardDto postcardDetails){
//            PostcardDto postcard = PostcardUtil.map(postcardServiceInterface.findByPostNumber(postNumber), PostcardDto.class);
//            postcard.setCountry(postcardDetails.getCountry());
//            postcard.setDateOfSend(postcardDetails.getDateOfSend());
//            postcard.setDateOfReceive(postcardDetails.getDateOfReceive());
//            return postcardServiceInterface.save(PostcardUtil.DtoToPostcard(postcard));
//        }

//todo: need to fix. finding by Id does't work.
//    @GetMapping(path = "/{postNumber}")
//    public Optional<Postcard> getPostcardById(@PathVariable("postNumber") String id) {
//        logger.debug(id.toString());
//        UUID uid = UUID.fromString("07c927c0-6c25-4317-a871-c6c2fa4504d5");
//        return postcardServiceInterface.findByPostcardId(uid);
//    }

