package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardConvertingException;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
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
    @Cacheable(value = "postcardCache")
    public List<PostcardDto> findAll() throws PostcardNotFoundException, PostcardException {
        try {
            postcardRepository.findAll();
        } catch (Exception e) {
            String message = "exception while getting all postcards";
            log.error(message);
            throw new PostcardException(message, e);
        }
        try {
            log.debug("get postcard list request...");
            return PostcardUtil.mapAll((List<Postcard>) postcardRepository.findAll(), PostcardDto.class);
        } catch (Exception e) {
            String message = "postcards not found...";
            log.error(message);
            throw new PostcardNotFoundException(message, e);
        }
    }

    @Override
    public void delete(UUID id) throws PostcardNotFoundException, PostcardException {
        log.debug("deleting postcard by id {}", id);
        Postcard postcard = postcardRepository.findByPostcardId(id);
        if (Objects.isNull(postcard)) {
            log.error("postcard with id {} not found", id);
            throw new PostcardNotFoundException("postcard not found in update service...");
        }
        try {
            postcardRepository.delete(postcard);
        } catch (Exception e) {
            String message = "exception while deleting postcard";
            log.error(message, e);
            throw new PostcardException(message, e);
        }

    }

    @Override
    @Cacheable(value = "postcardCache")
    public PostcardDto findByPostcardById(UUID id) throws PostcardConvertingException, PostcardNotFoundException, PostcardException {
        log.debug("finding postcard by id {}", id);
        try {
            postcardRepository.findByPostcardId(id);
        } catch (Exception e) {
            String message = "exception while getting postcard from db";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
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
    public Postcard createPostcard(PostcardDto postcardDto) throws PostcardConvertingException, PostcardException {
        log.debug("creating postcard with parameters {}", postcardDto);
        try {
            dtoToPostcard(postcardDto);
        } catch (Exception e) {
            String message = "error occurred during converting dto to postcard...";
            log.error(message, e);
            throw new PostcardConvertingException(message, e);
        }
        try {
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            String message = "exception while creating postcard";
            log.error(message);
            throw new PostcardException(message, e);
        }
    }

    @Override
    public List<Postcard> createPostcardList(List<PostcardDto> postcardDTOList) throws PostcardConvertingException, PostcardException {
        UUID userId = postcardDTOList.get(0).getUserId();
        postcardDTOList.forEach(postcard -> {
            if (Objects.isNull(userRepository.findUserById(userId))) {
                try {
                    throw new UserNotFoundException("user not found by creating list of postcards...");
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            log.debug("converting list of postcards...");
            PostcardUtil.mapAll(postcardDTOList, Postcard.class);
        } catch (Exception e) {
            String message = "error occurred during converting...";
            log.error(message);
            throw new PostcardConvertingException(message, e);
        }
        try {
            List<Postcard> postcardList = PostcardUtil.mapAll(postcardDTOList, Postcard.class);
            postcardList.forEach(postcard -> postcard.setUser(userRepository.findUserById(userId)));
            return (List<Postcard>) postcardRepository.saveAll(postcardList);
        } catch (Exception e) {
            String message = "exception while creating list of postcards";
            log.error(message);
            throw new PostcardException(message, e);
        }
    }

    @Override
    public Postcard updatePostcard(PostcardDto postcardDto) throws PostcardNotFoundException, PostcardConvertingException, PostcardException {
        log.debug("updating postcard with parameters {}", postcardDto);
        UUID id = postcardDto.getId();
        try {
            postcardRepository.findByPostcardId(id);
        } catch (Exception e) {
            String message = "exception while updating postcard";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
        Postcard postcardToUpdate = postcardRepository.findByPostcardId(id);
        if (Objects.isNull(postcardToUpdate)) {
            log.error("postcard not found by id {}", id);
            throw new PostcardNotFoundException("postcard not found in update service...");
        }
        try {
            log.debug("update postcard by id {}", id);
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            log.error("error occurred by mapping", e);
            throw new PostcardConvertingException();
        }
    }

    private Postcard dtoToPostcard(PostcardDto postcardDto) throws PostcardConvertingException, UserNotFoundException {
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

    private PostcardDto postcardToDTO(Postcard postcard) throws UserNotFoundException, PostcardConvertingException {
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
