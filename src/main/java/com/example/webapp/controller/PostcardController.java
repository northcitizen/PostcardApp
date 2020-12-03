package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
public class PostcardController {

    //todo: надо убрать
    private static final Logger logger = LoggerFactory.getLogger(PostcardController.class);
    // почему logger, а не LOGGER?

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostcardDto> postcardList() {
        // желательно перенести логику в сервис
        logger.debug("get postcard list request");
        try {
            return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
        } catch (Exception e) {
            logger.debug("mapping from postcard to postcardDto failed", e);
        }
        return null; // пользователь API не получит информацию о том, что произошла ошибка: кинуть эксепшен/отдавать запрос в другом виде
    }

    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        logger.debug("://" + postcardDto.toString());
        return postcardService.createPostcard(postcardDto);
    }

    @PostMapping(path = "/batch")
    // обычно называют createPostcardList
    public List<Postcard> createListPostcards(@RequestBody List<PostcardDto> postcardList) {
        return postcardService.createListPostcards(postcardList);
    }

    // открыток может быть много
    @GetMapping(path = "/users/{user_id}")/*postcard id! not user*/ /*добавили семантики параметру -> можем избавиться от комментария*/
    public PostcardDto getPostcardByUser(@PathVariable("user_id") UUID id) {/*id can be null*/ //TODO: delete comment, add semantics
        return PostcardUtil.map(postcardService.findByPostcardId(id), PostcardDto.class); // TODO: перенести в сервис
    }

    @PutMapping(path = "/{id}") //{id} избыточно
    public Postcard updatePostcard(@PathVariable("id") UUID id, // id избыточно
                                   @RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(id, postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostcard(@PathVariable("id") UUID id) {
        postcardService.delete(postcardService.findByPostcardId(id)); // TODO: перенести в сервис
    }
}