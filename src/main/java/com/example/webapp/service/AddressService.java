package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address save(Address address);

    void delete(UUID id);

    Address createAddress(AddressDto addressDto);

    AddressDto findAddressById(UUID id);

    Address updateAddress(AddressDto addressDto);

    List<AddressDto> findAll();
}
