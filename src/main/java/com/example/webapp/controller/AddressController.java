package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;
import com.example.webapp.service.AddressService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address createAddress(@RequestBody AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @GetMapping(path = "/{id}")
    public AddressDto getAddress(@PathVariable("id") UUID id) {
        return PostcardUtil.map(addressService.findAddressById(id), AddressDto.class);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable("id") UUID id) {
        addressService.delete(addressService.findAddressById(id));
    }

    @PutMapping(path = "/{id}")
    public Address updateAddress(@PathVariable("id") UUID id,
                                 @RequestBody AddressDto addressDto) {
        return addressService.updateAddress(id, addressDto);
    }
}
