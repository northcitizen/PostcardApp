package com.example.webapp.controller;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.service.PostcardService;
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
        return postcardService.findAll();
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
    public PostcardDto getPostcardById(@PathVariable("id") UUID id) {/*id can be null*/ //TODO: delete comments, add semantics
        return postcardService.findByPostcardById(id); // TODO: перенести в сервис
    }//забыл, что тут переносить в сервис

    @PutMapping //{id} избыточно //upd:это id открытки, то есть мне ее в dto перенести?
    public Postcard updatePostcard(@RequestBody PostcardDto postcardDetails) {
        return postcardService.updatePostcard(postcardDetails);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostcard(@PathVariable("id") UUID id) {
        postcardService.delete(id);
    }
}