package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    Address create(AddressDto addressDto) throws AddressException;

    AddressDto findById(UUID id) throws AddressException, UserNotFoundException;

    List<AddressDto> findAll() throws AddressConvertingException;

    Address update(AddressDto addressDto) throws AddressException;

    void delete(UUID id) throws AddressNotFoundException, AddressException;
}
