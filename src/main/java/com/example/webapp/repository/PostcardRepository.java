package com.example.webapp.repository;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import javafx.geometry.Pos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostcardRepository extends CrudRepository<Postcard, Long> {

    Optional<Postcard> findByDistance(Long distance);
    Optional<Postcard> findByDateOfReceive(String dateOfReceive);
    Optional<Postcard> findByCountry(String country);
    Optional<Postcard> findByPostNumber(String postNumber);
    List<Postcard> findByYear(int year);

}
