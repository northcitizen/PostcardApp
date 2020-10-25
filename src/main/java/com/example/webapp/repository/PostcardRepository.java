package com.example.webapp.repository;

import com.example.webapp.models.Postcard;
import org.springframework.data.repository.CrudRepository;

public interface PostcardRepository extends CrudRepository<Postcard, Long> {
}
