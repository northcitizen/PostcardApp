package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.address.LastAddressException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.User;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.AddressService;
import com.example.webapp.service.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@Transactional
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
    @Transactional
    public void delete(UUID id) {
        log.debug("deleting address by id {}", id);
        Address address = addressRepository.findAddressById(id);
        if (Objects.isNull(address)) {
            log.error("address with id {} not found", id);
            throw new AddressNotFoundException();
        }
        if (address.isStatus()) {
            log.error("can not delete address with id {}, because it's current address", id);
            throw new LastAddressException("can not delete the current address");
        }
        addressRepository.delete(address);
    }

    @Override
    public Address createAddress(AddressDto addressDto) {
        log.debug("creating address with parameter {}", addressDto);
        try {
            return addressRepository.save(dtoToAddress(addressDto));
        } catch (RuntimeException e) {
            log.error("error occurred during converting dto to address", e);
            throw new AddressConvertingException(e);
        }
    }

    @Override
    public AddressDto findById(UUID id) {
        log.debug("finding address by id {}", id);
        Address address = addressRepository.findAddressById(id);
        if (Objects.isNull(address)) {
            log.error("address not found by id {}", id);
            throw new AddressNotFoundException(id);
        }
        try {
            log.debug("find address by id {} request...", id);
            return addressToDTO(address);
        } catch (RuntimeException e) {
            log.error("error occurred during converting entity to DTO", e);
            throw new AddressConvertingException(id);
        }
    }

    @Override
    public Address updateAddress(AddressDto addressDto) {
        log.debug("updating address with parameters {}", addressDto);
        UUID id = addressDto.getId();
        Address addressToUpdate = addressRepository.findAddressById(id);
        if (Objects.isNull(addressToUpdate)) {
            log.error("address not found by id {}", id);
            throw new AddressNotFoundException(id);
        }
        try {
            log.debug("update postcard by id {}", id);
            return addressRepository.save(dtoToAddress(addressDto));
        } catch (RuntimeException e) {
            log.error("error occurred during converting dto to address", e);
            throw new AddressConvertingException(id);
        }
    }

    @Override
    public List<AddressDto> findAll() {
        try {
            log.debug("get addresses list request...");
            return PostcardUtil.mapAll((List<Address>) addressRepository.findAll(), AddressDto.class);
        } catch (RuntimeException e) {
            log.error("error occurred during mapping...");
            throw new AddressNotFoundException("address not found exception", e);
        }
    }

    private Address dtoToAddress(AddressDto addressDto) {
        UUID userId = addressDto.getUserId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with Id {} not found", userId);
            throw new UserNotFoundException(userId);
        }
        return Address.builder()
                .id(addressDto.getId())
                .building(addressDto.getBuilding())
                .postNumber(addressDto.getPostNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .status(addressDto.isStatus())
                .street(addressDto.getStreet())
                .user(user)
                .build();
    }

    private AddressDto addressToDTO(Address address) {
        UUID userId = address.getUser().getId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with Id {} not found", userId);
            throw new UserNotFoundException();
        }
        return AddressDto.builder()
                .id(address.getId())
                .building(address.getBuilding())
                .city(address.getCity())
                .country(address.getCountry())
                .postNumber(address.getPostNumber())
                .status(address.isStatus())
                .street(address.getStreet())
                .userId(userId)
                .build();
    }
}