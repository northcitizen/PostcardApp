package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressException;
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
    public void deleteTest() throws AddressException {
        Address address = mock(Address.class);
        when(addressRepository.findAddressById(any())).thenReturn(address);
        addressService.delete(address.getId());
        verify(addressRepository, times(1)).delete(address);
    }

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
}
