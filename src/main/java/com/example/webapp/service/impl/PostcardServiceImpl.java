package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.exception.postcard.PostcardNotSavedException;
import com.example.webapp.exception.postcard.PostcardNotUpdatedException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PostcardServiceImpl implements PostcardService {

    private final CacheManager cacheManager;//TODO : использовать кэш
    private final PostcardRepository postcardRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostcardServiceImpl(PostcardRepository postcardRepository,
                               CacheManager cacheManager,
                               UserRepository userRepository) {
        this.postcardRepository = postcardRepository;
        this.cacheManager = cacheManager;
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
        } catch (RuntimeException e) {
            log.error("postcards not found...");
            throw new PostcardNotFoundException();
        }
    }

    @Override
    @CacheEvict(value = "postcardCache", allEntries = true)
    public Postcard save(Postcard postcard) {
        return postcardRepository.save(postcard);
    }

    @Override
    public void delete(UUID id) {
        if (postcardRepository.findByPostcardId(id) == null)
            throw new PostcardNotFoundException("postcard not found in update service...");
        postcardRepository.delete(postcardRepository.findByPostcardId(id));
    }

    @Override
    @Cacheable(value = "postcardCache")
    public PostcardDto findByPostcardById(UUID id) {
        if (postcardRepository.findByPostcardId(id) == null)
            throw new PostcardNotFoundException("postcard not found...");
        try {
            log.debug("find postcard by id request...");
            return PostcardUtil.map(postcardRepository.findByPostcardId(id), PostcardDto.class);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping...", e);
            throw new PostcardNotFoundException();
        }

    }

    @Override
    public Postcard createPostcard(PostcardDto postcardDto) {
        if (userRepository.findUserById(postcardDto.getUser().getId()) == null)
            throw new UserNotFoundException("user not found by creating postcard...");
        try {
            log.debug("creating postcard");
            Postcard temp = PostcardUtil.map(postcardDto, Postcard.class);
            temp.setUser(userRepository.findUserById(postcardDto.getUser().getId()));
            return postcardRepository.save(temp);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping...", e);
            postcardRepository.deleteAll();
            throw new PostcardNotSavedException();
        }
    }

    @Override
    public List<Postcard> createPostcardList(List<PostcardDto> postcardList) {

        postcardList.forEach(postcard -> {
            if (userRepository.findUserById(postcard.getUser().getId()) == null)
                throw new UserNotFoundException("user not found by creating list of postcards...");
        });

        try {
            log.debug("creating list of postcards...");
            List<Postcard> postcardList1 = PostcardUtil.mapAll(postcardList, Postcard.class);
            postcardList1.forEach(postcard -> postcard.setUser(userRepository.findUserById(postcard.getUser().getId())));
            return (List<Postcard>) postcardRepository.saveAll(postcardList1);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping...", e);
            postcardRepository.deleteAll();
            throw new PostcardNotSavedException();
        }
    }

    @Override
    public Postcard updatePostcard(UUID id, PostcardDto postcardDto) {

        if (userRepository.findUserById(postcardDto.getUser().getId()) == null)
            throw new UserNotFoundException("user not found in update service...");
        if (postcardRepository.findByPostcardId(id) == null)
            throw new PostcardNotFoundException("postcard not found in update service...");
        try {
            log.debug("update postcard service...");
            Postcard postcard = PostcardUtil.map(postcardRepository.findByPostcardId(id), Postcard.class);
            Postcard postcardUpdate = PostcardUtil.map(postcardDto, Postcard.class);
            postcardUpdate.setUser(userRepository.findUserById(postcardDto.getUser().getId()));
            postcardUpdate.setId(id);
            BeanUtils.copyProperties(postcardUpdate, postcard);
            return postcardRepository.save(postcard);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping", e);
            throw new PostcardNotUpdatedException();
        }
    }
}
