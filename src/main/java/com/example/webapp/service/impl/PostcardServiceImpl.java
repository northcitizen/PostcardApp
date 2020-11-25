package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardBuilder;
import com.example.webapp.model.PostcardStatus;
import com.example.webapp.model.User;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.service.PostcardService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PostcardServiceImpl implements PostcardService {

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final CacheManager cacheManager;
    final PostcardRepository postcardRepository;

    @Autowired
    public PostcardServiceImpl(PostcardRepository postcardRepository, CacheManager cacheManager) {
        this.postcardRepository = postcardRepository;
        this.cacheManager = cacheManager;
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
    public void deleteById(UUID id) {
        postcardRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "postcardCache")
    public Postcard findByPostcardId(UUID id) {
        return postcardRepository.findByPostcardId(id);
    }

    @Override
    public Postcard add(String postNumber, String country, String name, String description,
                        Long distance, PostcardStatus status, String sendDate,
                        String receiveDate, User user) {

        LocalDateTime receiveDateParse = LocalDate.parse(receiveDate, dtf).atStartOfDay();
        LocalDateTime sendDateParse = LocalDate.parse(sendDate, dtf).atStartOfDay();

        return new PostcardBuilder()
                .setPostNumber(postNumber)
                .setCountry(country)
                .setName(name)
                .setDescription(description)
                .setDistance(distance)
                .setStatus(status)
                .setReceiveDate(sendDateParse)
                .setSendDate(receiveDateParse)
                .setUser(user)
                .getPostcard();
    }

    @Override
    public Postcard createPostcard(PostcardDto postcardDto) {

        LocalDateTime receiveDateParse = LocalDate.parse(postcardDto.getReceiveDate(), dtf).atStartOfDay();
        LocalDateTime sendDateParse = LocalDate.parse(postcardDto.getSendDate(), dtf).atStartOfDay();

        return postcardRepository.save(new PostcardBuilder()
                .setPostNumber(postcardDto.getPostNumber())
                .setCountry(postcardDto.getCountry())
                .setName(postcardDto.getName())
                .setDescription(postcardDto.getDescription())
                .setDistance(postcardDto.getDistance())
                .setStatus(postcardDto.getStatus())
                .setReceiveDate(receiveDateParse)
                .setSendDate(sendDateParse)
                .setUser(postcardDto.getUser())
                .getPostcard());
    }

    @Override
    public Postcard updatePostcard(UUID user_id, UUID id, PostcardDto postcardDto) {

        User user = new User();
        user.setId(user_id);
        user.setFirstName(postcardDto.getUser().getFirstName());
        user.setLastName(postcardDto.getUser().getLastName());
        user.setEmail(postcardDto.getUser().getEmail());

        PostcardDto postcard = PostcardUtil.map(postcardRepository.findByPostcardId(id), PostcardDto.class);
        postcard.setPid(id);
        postcard.setCountry(postcardDto.getCountry());
        postcard.setSendDate(postcardDto.getSendDate());
        postcard.setReceiveDate(postcardDto.getReceiveDate());
        postcard.setPostNumber(postcardDto.getPostNumber());
        postcard.setDistance(postcardDto.getDistance());
        postcard.setName(postcardDto.getName());
        postcard.setDistance(postcardDto.getDistance());
        postcard.setDescription(postcardDto.getDescription());
        postcard.setStatus(postcardDto.getStatus());
        postcard.setUser(user);

        return postcardRepository.save(PostcardUtil.map(postcard, Postcard.class));
    }
}
