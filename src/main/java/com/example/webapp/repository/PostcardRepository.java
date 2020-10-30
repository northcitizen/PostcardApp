package com.example.webapp.repository;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import org.springframework.data.repository.CrudRepository;

public interface PostcardRepository extends CrudRepository<Postcard, Long> {

}
