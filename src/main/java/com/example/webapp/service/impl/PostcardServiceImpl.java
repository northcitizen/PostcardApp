package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardConvertingException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.postcard.PostcardNotSavedException;
import com.example.webapp.exception.postcard.PostcardNotUpdatedException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class PostcardServiceImpl implements PostcardService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final PostcardRepository postcardRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostcardServiceImpl(PostcardRepository postcardRepository,
                               UserRepository userRepository) {
        this.postcardRepository = postcardRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "postcardCache", key = "#id", unless = "#result==null")
    public Postcard findByPostNumber(String postNumber) {
        return postcardRepository.findByPostNumber(postNumber);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public List<PostcardDto> findAll() {
        try {
            log.debug("get postcard list request...");
            return PostcardUtil.mapAll((List<Postcard>) postcardRepository.findAll(), PostcardDto.class);
        } catch (Exception e) {
            log.error("postcards not found...");
            throw new PostcardNotFoundException();
        }
    }

    @Override
    public void delete(UUID id) {
        log.debug("deleting postcard by id {}", id);
        Postcard postcard = postcardRepository.findByPostcardId(id);
        if (Objects.isNull(postcard)) {
            log.error("postcard with id {} not found", id);
            throw new PostcardNotFoundException("postcard not found in update service...");
        }
        postcardRepository.delete(postcard);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public PostcardDto findByPostcardById(UUID id) {
        log.debug("finding postcard by id {}", id);
        Postcard postcard = postcardRepository.findByPostcardId(id);
        if (Objects.isNull(postcard)) {
            log.error("postcard with id {} not found", id);
            throw new PostcardNotFoundException("postcard not found...");
        }
        try {
            return postcardToDTO(postcard);
        } catch (Exception e) {
            log.error("error occurred during converting postcard to dto...", e);
            throw new PostcardConvertingException(id);
        }

    }

    @Override
    public Postcard createPostcard(PostcardDto postcardDto) {
        log.debug("creating postcard with parameters {}", postcardDto);
        try {
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            log.error("error occurred during converting dto to postcard...", e);
            postcardRepository.deleteAll();
            throw new PostcardConvertingException(e);
        }
    }

    @Override
    public List<Postcard> createPostcardList(List<PostcardDto> postcardDTOList) {

        UUID userId  = postcardDTOList.get(0).getUserId();
        postcardDTOList.forEach(postcard -> {
            if (Objects.isNull(userRepository.findUserById(userId))) {
                throw new UserNotFoundException("user not found by creating list of postcards...");
            }
        });
        try {
            log.debug("creating list of postcards...");
            List<Postcard> postcardList = PostcardUtil.mapAll(postcardDTOList, Postcard.class);
            postcardList.forEach(postcard -> postcard.setUser(userRepository.findUserById(userId)));
            return (List<Postcard>) postcardRepository.saveAll(postcardList);
        } catch (Exception e) {
            log.error("error occurred by mapping...", e);
            postcardRepository.deleteAll();
            throw new PostcardNotSavedException();
        }
    }

    @Override
    public Postcard updatePostcard(PostcardDto postcardDto) {
        log.debug("updating postcard with parameters {}", postcardDto);
        UUID id = postcardDto.getId();
        Postcard postcardToUpdate = postcardRepository.findByPostcardId(id);
        if (Objects.isNull(postcardToUpdate)) {
            throw new PostcardNotFoundException("postcard not found in update service...");
        }
        try {
            log.debug("update postcard by id {}", id);
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            log.error("error occurred by mapping", e);
            throw new PostcardNotUpdatedException();
        }
    }

    private Postcard dtoToPostcard(PostcardDto postcardDto) {
        UUID userId = postcardDto.getUserId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with Id {} not found", userId);
            throw new UserNotFoundException(userId);
        }
        try {
            return buildPostcard(postcardDto, user);
        } catch (DateTimeParseException e) {
            log.error("error occurred during parsing date", e);
            throw new PostcardConvertingException(e);
        }
    }

    private PostcardDto postcardToDTO(Postcard postcard) {
        UUID userId = postcard.getUser().getId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with Id {} not found", userId);
            throw new UserNotFoundException(userId);
        }
        try {
            return buildPostcardDTO(postcard, userId);
        } catch (Exception e) {
            log.error("error occurred during converting", e);
            throw new PostcardConvertingException();
        }
    }

    private Postcard buildPostcard(PostcardDto postcardDto, User user) {
        return Postcard.builder()
                .id(postcardDto.getId())
                .postNumber(postcardDto.getPostNumber())
                .country(postcardDto.getCountry())
                .name(postcardDto.getName())
                .description(postcardDto.getDescription())
                .distance(postcardDto.getDistance())
                .status(postcardDto.getStatus())
                .sendDate(LocalDate.parse(postcardDto.getSendDate(), formatter).atStartOfDay())
                .receiveDate(LocalDate.parse(postcardDto.getReceiveDate(), formatter).atStartOfDay())
                .user(user)
                .build();
    }

    private PostcardDto buildPostcardDTO(Postcard postcard, UUID userId) {
        return PostcardDto.builder()
                .id(postcard.getId())
                .postNumber(postcard.getPostNumber())
                .country(postcard.getCountry())
                .description(postcard.getDescription())
                .distance(postcard.getDistance())
                .name(postcard.getName())
                .status(postcard.getStatus())
                .userId(userId)
                .receiveDate(postcard.getReceiveDate().toLocalDate().atStartOfDay().toString())
                .sendDate(postcard.getSendDate().toLocalDate().atStartOfDay().toString())
                .build();
    }
}
