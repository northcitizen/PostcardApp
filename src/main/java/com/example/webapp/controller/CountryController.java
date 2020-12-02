package com.example.webapp.controller;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;
import com.example.webapp.service.CountryService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(path = "/{id}")
    public Country createCountry(@RequestBody CountryDto countryDto,
                                 @PathVariable("id") UUID id) {
        return countryService.createCountry(countryDto, id);
    }

    @GetMapping(path = "/{id}")
    public CountryDto getCountry(@PathVariable("id") UUID id) {
        return PostcardUtil.map(countryService.findCountryById(id), CountryDto.class);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable("id") UUID id) {
        countryService.delete(countryService.findCountryById(id));
    }

    @PutMapping(path = "/{id}")
    public Country updatedCountry(@PathVariable("id") UUID id,
                                  @RequestBody CountryDto countryDto) {
        return countryService.updateCountry(id, countryDto);
    }
}
