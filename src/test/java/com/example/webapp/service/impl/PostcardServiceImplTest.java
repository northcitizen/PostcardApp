package com.example.webapp.service.impl;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.exception.postcard.PostcardException;
import com.example.webapp.exception.postcard.PostcardNotFoundException;
import com.example.webapp.model.Postcard;
import com.example.webapp.model.PostcardStatus;
import com.example.webapp.model.User;
import com.example.webapp.repository.PostcardRepository;
import com.example.webapp.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostcardServiceImplTest {

    @Mock
    private PostcardRepository postcardRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostcardServiceImpl postcardService;

    @Test
    public void createTest() throws PostcardException {
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = User.builder()
                .id(userID)
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        Postcard expectedPostcard = Postcard.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .receiveDate(LocalDate.parse("2020-01-01").atStartOfDay())
                .user(user)
                .build();
        PostcardDto postcardDto = PostcardDto.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate("2021-01-01")
                .receiveDate("2021-01-01")
                .userId(userID)
                .build();
        when(userRepository.findUserById(userID)).thenReturn(user);
        when(postcardRepository.save(any(Postcard.class))).thenReturn(expectedPostcard);
        Postcard actualPostcard = postcardService.create(postcardDto);
        Assertions.assertEquals(expectedPostcard, actualPostcard);
    }

    @Test
    public void createListTest() throws PostcardException {
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = User.builder()
                .id(userID)
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        List<Postcard> expectedPostcard = new ArrayList<>();
        expectedPostcard.add(Postcard.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .receiveDate(LocalDate.parse("2020-01-01").atStartOfDay())
                .user(user)
                .build());
        List<PostcardDto> postcardList = new ArrayList<>();
        postcardList.add(PostcardDto.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate("2021-01-01")
                .receiveDate("2021-01-01")
                .userId(userID)
                .build());

        when(userRepository.findUserById(userID)).thenReturn(user);
        when(postcardRepository.saveAll(any())).thenReturn(expectedPostcard);
        List<Postcard> actualPostcard = postcardService.createList(postcardList);
        Assertions.assertEquals(expectedPostcard, actualPostcard);
    }

    @Test
    public void findByIdTest() throws PostcardException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = mock(User.class);
        Postcard postcard = Postcard.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .receiveDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .user(user)
                .build();
        PostcardDto expectedPostcard = PostcardDto.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate("2021-01-01T00:00")
                .receiveDate("2021-01-01T00:00")
                .userId(user.getId())
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        when(postcardRepository.findByPostcardId(any())).thenReturn(postcard);
        PostcardDto actualPostcard = postcardService.findById(any());
        Assertions.assertEquals(expectedPostcard, actualPostcard);
    }

    @Test
    public void findAllTest() throws PostcardNotFoundException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        User user = User.builder()
                .firstName("Alex")
                .lastName("Fisher")
                .email("af@gmail.com")
                .addresses(Collections.emptyList())
                .postcards(Collections.emptyList())
                .build();
        List<Postcard> postcardList = new ArrayList<>();
        postcardList.add(Postcard.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .receiveDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .user(user)
                .build());
        List<PostcardDto> expectedPostcard = new ArrayList<>();
        expectedPostcard.add(PostcardDto.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate("2021-01-01T00:00")
                .receiveDate("2021-01-01T00:00")
                .build());
        when(postcardRepository.findAll()).thenReturn(postcardList);
        List<PostcardDto> actualPostcard = postcardService.findAll();
        Assertions.assertEquals(expectedPostcard, actualPostcard);
    }

    @Test
    public void updateTest() throws PostcardException {
        UUID id = UUID.fromString("a315e6ea-0c35-496e-9859-431211185371");
        UUID userID = UUID.fromString("adc6c0fd-e42d-4ed5-bee6-b815d0945ae0");
        User user = mock(User.class);
        Postcard postcard = mock(Postcard.class);
        Postcard expectedPostcard = Postcard.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .receiveDate(LocalDate.parse("2021-01-01").atStartOfDay())
                .user(user)
                .build();
        PostcardDto postcardDto = PostcardDto.builder()
                .id(id)
                .postNumber("NL-458778")
                .country("New Zealand")
                .name("Jack")
                .description("None")
                .distance(7856L)
                .status(PostcardStatus.RECEIVED)
                .sendDate("2021-01-01")
                .receiveDate("2021-01-01")
                .userId(userID)
                .build();
        when(userRepository.findUserById(any())).thenReturn(user);
        when(postcardRepository.findByPostcardId(any())).thenReturn(postcard);
        when(postcardRepository.save(any())).thenReturn(expectedPostcard);
        Postcard actualPostcard = postcardService.update(postcardDto);
        Assertions.assertEquals(expectedPostcard, actualPostcard);
    }

    @Test
    public void deleteTest() throws PostcardException {
        Postcard postcard = mock(Postcard.class);
        when(postcardRepository.findByPostcardId(any())).thenReturn(postcard);
        postcardService.delete(postcard.getId());
        verify(postcardRepository, times(1)).delete(postcard);
    }
}
