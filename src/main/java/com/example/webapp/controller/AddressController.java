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

    // Желательно придерживаться одного порядка (например, CRUD) и наименования во всех контроллерах/сервисах
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Address create(@RequestBody AddressDto addressDto) throws AddressException {
        log.debug("creating address with parameters {}", addressDto);
        return addressService.createAddress(addressDto);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AddressDto find(@PathVariable("id") UUID id) throws AddressException, AddressConvertingException, UserNotFoundException, AddressNotFoundException {
        log.debug("finding address by id {}", id);
        return addressService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) throws AddressException, AddressNotFoundException {
        log.debug("deleting address by id {}", id);
        addressService.delete(id);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Address update(@RequestBody AddressDto addressDto) throws AddressException, AddressNotFoundException, AddressConvertingException, UserNotFoundException {
        log.debug("updating address with parameters {}", addressDto);
        return addressService.updateAddress(addressDto);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AddressDto> findAll() throws AddressException, AddressNotFoundException, AddressConvertingException {
        log.debug("get addresses list request...");
        return addressService.findAll();
    }
}
