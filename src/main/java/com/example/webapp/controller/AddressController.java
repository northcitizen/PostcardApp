package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;
import com.example.webapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return addressService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable("id") UUID id) {
        addressService.delete(id);
    }

    @PutMapping
    public Address updateAddress(@RequestBody AddressDto addressDto) {
        return addressService.updateAddress(addressDto);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AddressDto> postcardList() {
        return addressService.findAll();
    }
}
