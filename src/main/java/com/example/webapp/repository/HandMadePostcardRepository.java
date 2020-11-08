package com.example.webapp.repository;

import com.example.webapp.model.Postcard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandMadePostcardRepository extends CrudRepository<Postcard, Long> {

    @Query("select p from Postcard p")
    List<Postcard> findAllPostcards();

    @Query("select p from Postcard p where p.year=?1")
    List<Postcard> getPostcardByYear(int year);

    @Query("select p from Postcard p where p.postNumber=?1")
    List<Postcard> getPostcardByPostNumber(String postNumber);

    @Query("select p from Postcard p where p.country=?1")
    List<Postcard> getPostcardByCountry(String country);

    @Query("select p from Postcard p where p.dateOfReceive=?1")
    List<Postcard> getPostcardByReceiveDate(String receiveDate);

    @Query("select p from Postcard p where p.distance=?1")
    List<Postcard> getPostcardByDistance(Long distance);
}
