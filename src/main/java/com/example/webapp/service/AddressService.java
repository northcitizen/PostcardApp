package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    Address create(AddressDto addressDto) throws AddressException;

    AddressDto findById(UUID id) throws AddressException;

    List<AddressDto> findAll() throws AddressException;

    Address update(AddressDto addressDto) throws AddressException;

    void delete(UUID id) throws AddressException;
}
