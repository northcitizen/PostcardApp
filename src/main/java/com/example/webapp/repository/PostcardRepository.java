package com.example.webapp.repository;

import com.example.webapp.model.Postcard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostcardRepository extends CrudRepository<Postcard, Long> {

    Optional<Postcard> findByDistance(Long distance);

    Optional<Postcard> findByDateOfReceive(String dateOfReceive);

    List<Postcard> findByCountry(String country);

    List<Postcard> findByPostNumber(String postNumber);

    List<Postcard> findByYear(int year);

    List<Postcard> findAllById(Long id);
    List<Postcard> findById(UUID id);
}
