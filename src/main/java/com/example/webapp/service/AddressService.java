package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    void delete(UUID id) throws AddressNotFoundException;

    Address createAddress(AddressDto addressDto) throws UserNotFoundException, AddressConvertingException;

    AddressDto findById(UUID id) throws AddressConvertingException, AddressNotFoundException, UserNotFoundException;

    Address updateAddress(AddressDto addressDto) throws AddressConvertingException, AddressNotFoundException, UserNotFoundException;

    List<AddressDto> findAll() throws AddressNotFoundException, AddressConvertingException;
}
