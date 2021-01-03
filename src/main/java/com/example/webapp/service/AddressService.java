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

    // вспоминаем о правиле: throws 1 exception, скрываем детали реализации

    void delete(UUID id) throws AddressNotFoundException, AddressException;

    Address createAddress(AddressDto addressDto) throws AddressException;

    AddressDto findById(UUID id) throws AddressConvertingException, AddressNotFoundException, UserNotFoundException, AddressException;

    Address updateAddress(AddressDto addressDto) throws AddressException, AddressConvertingException, AddressNotFoundException, UserNotFoundException;

    List<AddressDto> findAll() throws AddressNotFoundException, AddressConvertingException, AddressException;

    Address convertDtoToAddress(AddressDto addressDto) throws AddressConvertingException, UserNotFoundException;

    AddressDto convertAddressToDTO(Address address) throws UserNotFoundException, AddressConvertingException;
}
