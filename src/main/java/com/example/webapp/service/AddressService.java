package com.example.webapp.service;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;

import java.util.UUID;

public interface AddressService {
    Address save(Address address);

    Address createAddress(AddressDto addressDto, UUID id);
}
