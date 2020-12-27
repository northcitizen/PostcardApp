package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.address.DeleteActiveAddressException;
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
    @Transactional
    public void delete(UUID id) throws AddressNotFoundException, AddressException {
        Address address = addressRepository.findAddressById(id);
        if (Objects.isNull(address)) {
            log.error("address with id {} not found", id);
            throw new AddressNotFoundException();
        }
        if (address.isActive()) {
            log.error("can not delete address with id {}, because it's active", id);
            throw new DeleteActiveAddressException("can not delete active address");
        }
        try {
            addressRepository.delete(address);
        } catch (Exception e) {
            throw new AddressException("exception while deleting address with id=\"" + id + "\"", e);
        }

    }

    @Override
    public Address createAddress(AddressDto addressDto) throws AddressException {
        log.debug("creating address with parameter {}", addressDto);
        try {
            return addressRepository.save(convertDtoToAddress(addressDto));
        } catch (Exception e) {
            String message = "exception while creating address";
            log.error(message, e);
            throw new AddressException(message, e);
        }
    }

    @Override
    public AddressDto findById(UUID id) throws AddressNotFoundException, AddressException {
        Address address = addressRepository.findAddressById(id);
        if (Objects.isNull(address)) {
            log.error("address with id {} not found", id);
            throw new AddressNotFoundException(id);
        }
        try {
            return convertAddressToDTO(address);
        } catch (Exception e) {
            throw new AddressException("exception while finding address with id=\"" + id + "\"", e);
        }

    }

    @Override
    public Address updateAddress(AddressDto addressDto) throws AddressException, AddressNotFoundException {
        UUID id = addressDto.getId();
        Address addressToUpdate = addressRepository.findAddressById(id);
        if (Objects.isNull(addressToUpdate)) {
            log.error("address with id {} not found", id);
            throw new AddressNotFoundException(id);
        }
        try {
            return addressRepository.save(convertDtoToAddress(addressDto));
        } catch (Exception e) {
            throw new AddressException("exception while updating address", e);
        }
    }

    @Override
    public List<AddressDto> findAll() throws AddressConvertingException, AddressException {
        try {
            addressRepository.findAll();
        } catch (Exception e) {
            String message = "exception while finding addresses";
            log.error(message);
            throw new AddressException(message, e);
        }
        try {
            List<Address> addresses = (List<Address>) addressRepository.findAll();
            return PostcardUtil.mapAll(addresses, AddressDto.class);
        } catch (Exception e) {
            String message = "error occurred during mapping addresses";
            log.error(message, e);
            throw new AddressConvertingException(message, e);
        }
    }

    private Address convertDtoToAddress(AddressDto addressDto) throws AddressConvertingException, UserNotFoundException {
        if (Objects.isNull(addressDto)) {
            String message = "can not convert null value to address";
            log.error(message);
            throw new AddressConvertingException(message);
        }
        UUID userId = addressDto.getUserId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with id {} not found", userId);
            throw new UserNotFoundException(userId);
        }
        return Address.builder()
                .id(addressDto.getId())
                .building(addressDto.getBuilding())
                .postNumber(addressDto.getPostNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .active(addressDto.isStatus())
                .street(addressDto.getStreet())
                .user(user)
                .build();
    }

    private AddressDto convertAddressToDTO(Address address) throws UserNotFoundException, AddressConvertingException {
        if (Objects.isNull(address)) {
            String message = "can not convert null value to address dto";
            log.error(message);
            throw new AddressConvertingException(message);
        }
        UUID userId = address.getUser().getId();
        User user = userRepository.findUserById(userId);
        if (Objects.isNull(user)) {
            log.error("user with Id {} not found", userId);
            throw new UserNotFoundException(userId);
        }
        return AddressDto.builder()
                .id(address.getId())
                .building(address.getBuilding())
                .city(address.getCity())
                .country(address.getCountry())
                .postNumber(address.getPostNumber())
                .status(address.isActive())
                .street(address.getStreet())
                .userId(userId)
                .build();
    }
}