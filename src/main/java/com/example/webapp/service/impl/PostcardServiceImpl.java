package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.user.UserException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.User;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.util.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public Postcard create(PostcardDto postcardDto) throws PostcardException {
        log.debug("creating postcard with parameters {}", postcardDto);
        try {
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            String message = "exception while creating postcard";
            log.error(message);
            throw new PostcardException(message, e);
        }
    }

    @Override
    public List<Postcard> createList(List<PostcardDto> postcardDtoList) throws PostcardException {
        log.debug("creating list of postcards {}", postcardDtoList);
        UUID userId;
        if (Objects.isNull(postcardDtoList) | postcardDtoList.isEmpty()) {
            String message = "list is null or empty";
            log.warn(message);
            throw new PostcardException(message);
        }
        userId = postcardDtoList.get(0).getUserId();
        try {
            User user;
            try {
                user = userRepository.findUserById(userId);
                if (Objects.isNull(user)) {
                    log.error("user with id {} not found", userId);
                    throw new UserNotFoundException(userId);
                }
            } catch (Exception e) {
                String message = "exception while getting user from db";
                log.error(message);
                throw new UserException(message, e);
            }
            List<Postcard> postcardList = PostcardUtil.mapAll(postcardDtoList, Postcard.class);
            postcardList.forEach(postcard -> {
                postcard.setUser(user);
            });
            return (List<Postcard>) postcardRepository.saveAll(postcardList);
        } catch (Exception e) {
            String message = "exception while creating list of postcards";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
    }

    @Override
    @Cacheable(value = "postcardCache")
    public PostcardDto findById(UUID id) throws PostcardException {
        log.debug("finding postcard by id {}", id);
        Postcard postcard;
        try {
            postcard = postcardRepository.findByPostcardId(id);
            if (Objects.isNull(postcard)) {
                log.error("postcard with id {} not found", id);
                throw new PostcardNotFoundException(id);
            }
        } catch (Exception e) {
            String message = "exception while getting postcard from db";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
        return postcardToDTO(postcard);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public List<PostcardDto> findAll() throws PostcardNotFoundException {
        log.debug("get postcard list request...");
        try {
            List<Postcard> all;
            try {
                all = (List<Postcard>) postcardRepository.findAll();
            } catch (Exception e) {
                String message = "exception while getting postcard from db";
                log.error(message, e);
                throw new PostcardException(message, e);
            }
            return PostcardUtil.mapAll(all, PostcardDto.class);
        } catch (Exception e) {
            String message = "postcards not found...";
            log.error(message);
            throw new PostcardNotFoundException(message, e);
        }
    }

    @Override
    public Postcard update(PostcardDto postcardDto) throws PostcardException {
        log.debug("updating postcard with parameters {}", postcardDto);
        if (Objects.isNull(postcardDto)) {
            String message = "postcardDto is null";
            log.warn(message);
            throw new PostcardException(message);
        }
        UUID id = postcardDto.getId();
        Postcard postcardToUpdate;
        try {
            try {
                postcardToUpdate = postcardRepository.findByPostcardId(id);
                if (Objects.isNull(postcardToUpdate)) {
                    log.error("postcard not found by id {}", id);
                    throw new PostcardNotFoundException(id);
                }
            } catch (Exception e) {
                String message = "exception while updating postcard";
                log.error(message, e);
                throw new PostcardException(message, e);
            }
            return postcardRepository.save(dtoToPostcard(postcardDto));
        } catch (Exception e) {
            String message = "exception while saving postcard";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
    }

    @Override
    public void delete(UUID id) throws PostcardException {
        log.debug("deleting postcard by id {}", id);
        Postcard postcard;
        try {
            try {
                postcard = postcardRepository.findByPostcardId(id);
                if (Objects.isNull(postcard)) {
                    log.error("postcard with id {} not found", id);
                    throw new PostcardNotFoundException(id);
                }
            } catch (Exception e) {
                String message = "exception while finding postcard";
                log.error(message, e);
                throw new PostcardException(message, e);
            }
            postcardRepository.delete(postcard);
        } catch (Exception e) {
            String message = "exception while deleting postcard";
            log.error(message, e);
            throw new PostcardException(message, e);
        }

    }

    private Postcard dtoToPostcard(PostcardDto postcardDto) throws PostcardException {
        if (Objects.isNull(postcardDto)) {
            String message = "postcardDto is null";
            log.warn(message);
            throw new PostcardException(message);
        }
        UUID userId = postcardDto.getUserId();
        User user;
        try {
            user = userRepository.findUserById(userId);
            if (Objects.isNull(user)) {
                log.error("user with Id {} not found", userId);
                throw new UserNotFoundException(userId);
            }
        } catch (Exception e) {
            String message = "exception while getting user from db";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
        return buildPostcard(postcardDto, user);
    }

    private PostcardDto postcardToDTO(Postcard postcard) throws PostcardException {
        if (Objects.isNull(postcard)) {
            String message = "postcard is null";
            log.warn(message);
            throw new PostcardException(message);
        }
        UUID userId = postcard.getUser().getId();
        try {
            User user = userRepository.findUserById(userId);
            if (Objects.isNull(user)) {
                log.error("user with Id {} not found", userId);
                throw new UserNotFoundException(userId);
            }
        } catch (Exception e) {
            String message = "exception while getting user from db";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
        return buildPostcardDTO(postcard, userId);
    }

    private Postcard buildPostcard(PostcardDto postcardDto, User user) throws PostcardException {
        try {
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
        } catch (Exception e) {
            String message = "error occurred during building postcard entity";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
    }

    private PostcardDto buildPostcardDTO(Postcard postcard, UUID userId) throws PostcardException {
        try {
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
        } catch (Exception e) {
            String message = "error occurred during building postcard dto";
            log.error(message, e);
            throw new PostcardException(message, e);
        }
    }
}
