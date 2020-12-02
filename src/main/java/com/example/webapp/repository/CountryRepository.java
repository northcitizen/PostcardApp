package com.example.webapp.repository;

import com.example.webapp.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepository extends CrudRepository<Country, UUID> {
    Country findCountryById(UUID id);
}
