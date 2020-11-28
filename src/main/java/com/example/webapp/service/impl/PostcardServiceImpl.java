package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.PostcardNotSavedException;
import com.example.webapp.model.Postcard;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostcardServiceImpl implements PostcardService {

    final CacheManager cacheManager;
    final PostcardRepository postcardRepository;
    final UserRepository userRepository;

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
    public List<Postcard> findAll() {
        return (List<Postcard>) postcardRepository.findAll();
    }

    @Override
    @CacheEvict(value = "postcardCache", allEntries = true)
    public Postcard save(Postcard postcard) {
        return postcardRepository.save(postcard);
    }

    @Override
    public void delete(Postcard postcard) {
        postcardRepository.delete(postcard);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public Postcard findByPostcardId(UUID id) {
        return postcardRepository.findByPostcardId(id);
    }

    @Override
    public Postcard createPostcard(PostcardDto postcardDto, UUID id) {

        try {
            Postcard temp = PostcardUtil.map(postcardDto, Postcard.class);
            temp.setUser(userRepository.findUserById(id));
            return postcardRepository.save(temp);
        } catch (RuntimeException e) {
            postcardRepository.deleteAll();
            throw new PostcardNotSavedException();
        }
    }

    @Override
    public List<Postcard> createListPostcards(List<PostcardDto> postcardList, UUID id) {
        try {
            List<Postcard> postcardList1 = PostcardUtil.mapAll(postcardList, Postcard.class);
            postcardList1.forEach(postcard -> postcard.setUser(userRepository.findUserById(id)));
            return (List<Postcard>) postcardRepository.saveAll(postcardList1);
        } catch (RuntimeException e) {
            postcardRepository.deleteAll();
            throw new PostcardNotSavedException();
        }
    }

    @Override
    public Postcard updatePostcard(UUID user_id, UUID id, PostcardDto postcardDto) {
        Postcard postcard = PostcardUtil.map(postcardRepository.findByPostcardId(id), Postcard.class);
        Postcard postcardUpdate = PostcardUtil.map(postcardDto, Postcard.class);
        postcardUpdate.setUser(userRepository.findUserById(user_id));
        postcardUpdate.setPid(id);
        BeanUtils.copyProperties(postcardUpdate, postcard);
        return postcardRepository.save(postcard);
    }
}
