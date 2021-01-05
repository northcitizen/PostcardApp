package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressConvertingException;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.user.UserNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.User;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void creatTest() throws AddressException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        User user = mock(User.class);
        AddressDto addressDto = AddressDto.builder()
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .status(false)
                .userId(userID)
                .build();
        Address expectedAddress = Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .user(user)
                .build();
        when(userRepository.findUserById(userID)).thenReturn(user);
        when(addressRepository.save(any(Address.class))).thenReturn(expectedAddress);
        Address actualAddress = addressService.create(addressDto);
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void findByIdTest() throws UserNotFoundException, AddressException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = mock(User.class);
        Address address = Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .user(user)
                .build();
        AddressDto expectedAddress = AddressDto.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .status(false)
                .userId(user.getId())
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        when(addressRepository.findAddressById(any())).thenReturn(address);
        AddressDto actualAddress = addressService.findById(any());
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void findAllTest() throws AddressConvertingException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = mock(User.class);
        List<Address> address = new ArrayList<>();
        address.add(Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .user(user)
                .build());
        List<AddressDto> expectedAddress = new ArrayList<>();
        expectedAddress.add(AddressDto.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .status(false)
                .userId(user.getId())
                .build());
        when(addressRepository.findAll()).thenReturn(address);
        List<AddressDto> actualAddress = addressService.findAll();
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void updateTest() throws AddressException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        User user = mock(User.class);
        Address address = mock(Address.class);
        Address expectedAddress = Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .user(user)
                .build();
        AddressDto addressDto = AddressDto.builder()
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .status(false)
                .userId(userID)
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        when(addressRepository.findAddressById(any())).thenReturn(address);
        when(addressRepository.save(any())).thenReturn(expectedAddress);
        Address actualAddress = addressService.update(addressDto);
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void deleteTest() throws AddressException {
        Address address = mock(Address.class);
        when(addressRepository.findAddressById(any())).thenReturn(address);
        addressService.delete(address.getId());
        verify(addressRepository, times(1)).delete(address);
    }
}
