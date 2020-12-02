package com.example.webapp.service;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;

import java.util.UUID;

public interface CountryService {

    Country save(Country country);

    Country createCountry(CountryDto countryDto, UUID id);

    Country findCountryById(UUID id);

    void delete(Country country);

    Country updateCountry(UUID id, CountryDto countryDto);
}
