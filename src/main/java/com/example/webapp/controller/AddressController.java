package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;
import com.example.webapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {

    final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path = "/{id}")
    public Address createAddress(@RequestBody AddressDto addressDto,
                                 @PathVariable("id") UUID id) {
        return addressService.createAddress(addressDto, id);
    }

}
