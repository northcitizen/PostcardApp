package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.model.Address;
import com.example.webapp.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/addresses")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Address create(@RequestBody AddressDto addressDto) throws AddressException {
        log.debug("creating address with parameters {}", addressDto);
        return addressService.createAddress(addressDto);
    }

    @GetMapping(path = "/{id}")
    public AddressDto find(@PathVariable("id") UUID id) throws AddressException {
        log.debug("finding address by id {}", id);
        try {
            return addressService.findById(id);
        } catch (Exception e) {
            throw new AddressException("exception while finding address with id=\"" + id + "\"", e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") UUID id) throws AddressException {
        log.debug("deleting address by id {}", id);
        try {
            addressService.delete(id);
        } catch (Exception e) {
            throw new AddressException("exception while deleting address with id=\"" + id + "\"", e);
        }
    }

    @PutMapping
    public Address update(@RequestBody AddressDto addressDto) throws AddressException {
        log.debug("updating address with parameters {}", addressDto);
        try {
            return addressService.updateAddress(addressDto);
        } catch (Exception e) {
            throw new AddressException("exception while updating address", e);
        }
    }

    @GetMapping
    public List<AddressDto> findAll() throws AddressException {
        log.debug("get addresses list request...");
        try {
            return addressService.findAll();
        } catch (Exception e) {
            throw new AddressException("exception while finding addresses", e);
        }
    }
}
