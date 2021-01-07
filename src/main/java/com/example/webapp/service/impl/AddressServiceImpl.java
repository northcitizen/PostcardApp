package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.User;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.AddressService;
import com.example.webapp.service.util.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
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
    public Address create(AddressDto addressDto) throws AddressException {
        log.debug("creating address with parameter {}", addressDto);
        try {
            Address address = convertDtoToAddress(addressDto);
            return addressRepository.save(address);
        } catch (Exception e) {
            String message = "exception while creating address";
            log.error(message, e);
            throw new AddressException(message, e);
        }
    }

    @Override
    public AddressDto findById(UUID id) throws AddressException, UserNotFoundException {
        log.debug("finding address with id {}", id);
        try {
            Address address = addressRepository.findAddressById(id);
            if (Objects.isNull(address)) {
                log.error("address with id {} not found", id);
                throw new AddressNotFoundException(id);
            }
            return convertAddressToDTO(address);
        } catch (Exception e) {
            String message = "exception while finding address with id ";
            log.error(message, e);
            throw new AddressException(message + id, e);
        }
    }

    @Override
    public List<AddressDto> findAll() throws AddressException {
        log.debug("getting address list");
        try {
            List<Address> addresses = (List<Address>) addressRepository.findAll();
            return PostcardUtil.mapAll(addresses, AddressDto.class);
        } catch (Exception e) {
            String message = "error occurred during getting address list";
            log.error(message, e);
            throw new AddressException(message, e);
        }
    }

    @Override
    @Transactional
    public Address update(AddressDto addressDto) throws AddressException {
        log.debug("updating address with parameters {}", addressDto);
        if (Objects.isNull(addressDto)) {
            String message = "address dto is null";
            log.error(message);
            throw new AddressException(message);
        }

        try {
            UUID id = addressDto.getId();
            Address addressToUpdate = addressRepository.findAddressById(id);
            if (Objects.isNull(addressToUpdate)) {
                log.error("address with id {} not found", id);
                throw new AddressNotFoundException(id);
            }
            return addressRepository.save(convertDtoToAddress(addressDto));
        } catch (Exception e) {
            String message = "exception while updating address";
            log.error(message);
            throw new AddressException(message, e);
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) throws AddressException {
        log.debug("deleting address with id {}", id);
        try {
            Address address = addressRepository.findAddressById(id);
            if (Objects.isNull(address)) {
                log.error("address with id {} not found", id);
                throw new AddressNotFoundException(id);
            }
            addressRepository.delete(address);
        } catch (Exception e) {
            String message = "exception while deleting address with id=\"" + id + "\"";
            log.error(message, e);
            throw new AddressException(message, e);
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

    private AddressDto convertAddressToDTO(Address address) throws UserNotFoundException, AddressException {
        if (Objects.isNull(address)) {
            String message = "address is null";
            log.error(message);
            throw new AddressException(message);
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