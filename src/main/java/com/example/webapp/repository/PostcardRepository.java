package com.example.webapp.repository;

import com.example.webapp.model.Postcard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostcardRepository extends CrudRepository<Postcard, UUID> {

    @Query("select p from Postcard p where p.id = ?1")
    Postcard findByPostcardId(UUID id);
}