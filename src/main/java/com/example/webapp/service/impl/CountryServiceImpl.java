package com.example.webapp.service.impl;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;
import com.example.webapp.repository.CountryRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.CountryService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    final CountryRepository countryRepository;
    final UserRepository userRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country createCountry(CountryDto countryDto, UUID id) {
     Country country = PostcardUtil.map(countryDto, Country.class);
     country.setUser(userRepository.findUserById(id));
        return countryRepository.save(country);
    }
}
