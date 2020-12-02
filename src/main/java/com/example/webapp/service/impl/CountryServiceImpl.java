package com.example.webapp.service.impl;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;
import com.example.webapp.repository.CountryRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.CountryService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;

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
        country.setUser(userRepository.findUserById(country.getUser().getId()));
        return countryRepository.save(country);
    }

    @Override
    public Country findCountryById(UUID id) throws NullPointerException {
        return countryRepository.findCountryById(id);
    }

    @Override
    public void delete(Country country) {
        countryRepository.delete(country);
    }

    @Override
    public Country updateCountry(UUID id, CountryDto countryDto) {
        Country country = PostcardUtil.map(countryRepository.findCountryById(id), Country.class);
        Country countryUpdate = PostcardUtil.map(countryDto, Country.class);
        countryUpdate.setUser(userRepository.findUserById(countryDto.getUser().getId()));
        countryUpdate.setId(id);
        BeanUtils.copyProperties(countryUpdate, country);
        return countryRepository.save(country);
    }
}
