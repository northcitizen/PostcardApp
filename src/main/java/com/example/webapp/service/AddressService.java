package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;

import java.util.UUID;

public interface AddressService {
    Address save(Address address);

    void delete(Address address);

    Address createAddress(AddressDto addressDto);

    Address findAddressById(UUID id);

    Address updateAddress(UUID id, AddressDto addressDto);
}
