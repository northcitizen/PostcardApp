package com.example.webapp.controller;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
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
        return addressService.create(addressDto);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AddressDto get(@PathVariable("id") UUID id) throws AddressException, UserNotFoundException {
        log.debug("finding address by id {}", id);
        return addressService.findById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AddressDto> getAll() throws AddressConvertingException, AddressException {
        log.debug("get addresses list request...");
        return addressService.findAll();
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Address update(@RequestBody AddressDto addressDto) throws AddressException, AddressNotFoundException, AddressConvertingException, UserNotFoundException {
        log.debug("updating address with parameters {}", addressDto);
        return addressService.update(addressDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) throws AddressException, AddressNotFoundException {
        log.debug("deleting address by id {}", id);
        addressService.delete(id);
    }
}
