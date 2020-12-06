package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.exception.address.AddressNotSavedException;
import com.example.webapp.exception.address.AddressNotUpdatedException;
import com.example.webapp.exception.address.LastAddressException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import com.example.webapp.service.AddressService;
import com.example.webapp.service.PostcardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(UUID id) {
        if (addressRepository.findAddressById(id) == null)
            throw new AddressNotFoundException("address not found in delete service...");
        if (addressRepository.findAddressById(id).getStatus())
            throw new LastAddressException("can not delete the current address");
        addressRepository.delete(addressRepository.findAddressById(id));
    }

    @Override
    public Address createAddress(AddressDto addressDto) {
        if (userRepository.findUserById(addressDto.getUser().getId()) == null)
            throw new AddressNotFoundException("user not found by creating address...");
        try {
            log.debug("creating address");
            Address address = PostcardUtil.map(addressDto, Address.class);
            address.setUser(userRepository.findUserById(address.getUser().getId()));
            return addressRepository.save(address);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping...", e);
            throw new AddressNotSavedException();
        }
    }

    @Override
    public AddressDto findAddressById(UUID id) {
        if (addressRepository.findAddressById(id) == null)
            throw new AddressNotFoundException("address not found in");
        try {
            log.debug("find address by id request...");
            return PostcardUtil.map(addressRepository.findAddressById(id), AddressDto.class);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping", e);
            throw new AddressNotFoundException();
        }
    }

    @Override
    public Address updateAddress(AddressDto addressDto) {
        if (addressRepository.findAddressById(addressDto.getId()) == null)
            throw new AddressNotFoundException("address not found...");
        if (userRepository.findUserById(addressDto.getUser().getId()) == null)
            throw new UserNotFoundException("user not found...");
        try {
            log.debug("update");
            Address address = PostcardUtil.map(addressRepository.findAddressById(addressDto.getId()), Address.class);
            Address addressUpdate = PostcardUtil.map(addressDto, Address.class);
            addressUpdate.setUser(userRepository.findUserById(addressDto.getUser().getId()));
            addressUpdate.setId(addressDto.getId());
            BeanUtils.copyProperties(addressUpdate, address);
            return addressRepository.save(address);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping in update address service...", e);
            throw new AddressNotUpdatedException();
        }
    }

    @Override
    public List<AddressDto> findAll() {
        try {
            log.debug("get addresses list request...");
            return PostcardUtil.mapAll((List<Address>) addressRepository.findAll(), AddressDto.class);
        } catch (RuntimeException e) {
            log.error("error occurred by mapping...");
            throw new AddressNotFoundException();
        }
    }
}