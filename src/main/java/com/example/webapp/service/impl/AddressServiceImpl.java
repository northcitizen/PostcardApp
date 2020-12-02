package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.model.Address;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.AddressService;
import com.example.webapp.service.PostcardUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public Address createAddress(AddressDto addressDto) {
        Address address = PostcardUtil.map(addressDto, Address.class);
        address.setUser(userRepository.findUserById(address.getUser().getId()));
        return addressRepository.save(address);
    }

    @Override
    public Address findAddressById(UUID id) throws NullPointerException {
        return addressRepository.findAddressById(id);
    }

    @Override
    public Address updateAddress(UUID id, AddressDto addressDto) {
        Address address = PostcardUtil.map(addressRepository.findAddressById(id), Address.class);
        Address addressUpdate = PostcardUtil.map(addressDto, Address.class);
        addressUpdate.setUser(userRepository.findUserById(addressDto.getUser().getId()));
        addressUpdate.setId(id);
        BeanUtils.copyProperties(addressUpdate, address);
        return addressRepository.save(address);
    }
}