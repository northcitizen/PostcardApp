package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.user.UserNotFoundException;
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
    public Address create(@RequestBody AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @GetMapping(path = "/{id}")
    public AddressDto get(@PathVariable("id") UUID id) {
        try {
            return addressService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") UUID id) {
        addressService.delete(id);
    }

    @PutMapping
    public Address update(@RequestBody AddressDto addressDto) {
        return addressService.updateAddress(addressDto);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AddressDto> getAll() {
        return addressService.findAll();
    }
}
