package com.example.webapp.service.impl;

import com.example.webapp.dto.AddressDto;
import com.example.webapp.exception.address.AddressException;
import com.example.webapp.exception.address.AddressNotFoundException;
import com.example.webapp.model.Address;
import com.example.webapp.model.User;
import com.example.webapp.repository.AddressRepository;
import com.example.webapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressServiceImplTest {

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void deleteTest() throws AddressNotFoundException, AddressException {
        UUID id = UUID.fromString("2b8892dc-4e99-4993-a8f3-ffdfd15b7d1c");
        Address address = Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .build();
        when(addressRepository.findAddressById(id)).thenReturn(address);
        addressService.delete(address.getId());
        verify(addressRepository, times(1)).delete(address);
    }

    @Test
    public void createAddressTest() throws AddressException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        User user = User.builder()
                .id(userID)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();

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

        Address address1 = Address.builder()
                .id(id)
                .building("1")
                .street("GreenWay")
                .city("Shire")
                .postNumber("025896")
                .country("New Zealand")
                .active(false)
                .user(user)
                .build();

        AddressDto addressDto = new AddressDto();
        addressDto.setId(id);
        addressDto.setBuilding("1");
        addressDto.setStreet("GreenWay");
        addressDto.setCity("Shire");
        addressDto.setPostNumber("025896");
        addressDto.setCountry("New Zealand");
        addressDto.setStatus(false);
        addressDto.setUserId(userID);


        when(userRepository.findUserById(userID)).thenReturn(user);
        when(addressRepository.save(address)).thenReturn(address1);
        when(addressService.createAddress(addressDto)).thenReturn(address);
        Assertions.assertEquals(address, address1);
    }
}
