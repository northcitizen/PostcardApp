package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/postcards")
@Slf4j
public class PostcardController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostcardDto> postcardList() {
        return PostcardUtil.mapAll(postcardService.findAll(), PostcardDto.class);
    }

    @PostMapping
    public Postcard createPostcard(@RequestBody PostcardDto postcardDto) {
        log.debug("://" + postcardDto.toString());
        return postcardService.createPostcard(postcardDto);
    }

    @PostMapping(path = "/batch")
    public List<Postcard> createPostcardList(@RequestBody List<PostcardDto> postcardList) {
        return postcardService.createPostcardList(postcardList);
    }

    // открыток может быть много ***upd***: нет, тут одна открытка, так как ищем открытку по id
    @GetMapping(path = "/{id}")/*postcard id! not user*/ /*добавили семантики параметру -> можем избавиться от комментария*/
    public PostcardDto getPostcardById(@PathVariable("id") UUID userId) {/*id can be null*/ //TODO: delete comments, add semantics
        return PostcardUtil.map(postcardService.findByPostcardById(userId), PostcardDto.class); // TODO: перенести в сервис
    }//забыл, что тут переносить в сервис

    @PutMapping(path = "/{id}") //{id} избыточно //upd:это id открытки, то есть мне ее в dto перенести?
    public Postcard updatePostcard(@PathVariable("id") UUID id, // id избыточно
                                   @RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(id, postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostcard(@PathVariable("id") UUID id) {
        postcardService.delete(id);
    }
}