package com.example.webapp.repository;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostcardRepository extends CrudRepository<Postcard, Long> {

    Optional<Postcard> findByDistance(Long distance);
}
