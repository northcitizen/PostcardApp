package com.example.webapp.controller;

import com.example.webapp.dto.CountryDto;
import com.example.webapp.model.Country;
import com.example.webapp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(path = "/{id}")
    public Country createCountry(@RequestBody CountryDto countryDto,
                                 @PathVariable("id") UUID id){
        return countryService.createCountry(countryDto, id);
    }
}
