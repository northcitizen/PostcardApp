package com.example.webapp.service;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;

import java.util.UUID;

public interface CountryService {

    Country save(Country country);

    Country createCountry(CountryDto countryDto, UUID id);
}
