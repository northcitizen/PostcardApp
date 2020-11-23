package com.example.webapp.service;

import com.example.webapp.dto.PostcardDto;
import com.example.webapp.model.Postcard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = UserMapper.class)
public interface PostcardMapper {
    @Mapping(target = "user.postcards", ignore = true)
    PostcardDto toDto(Postcard postcard);

    @Named("postcardDTOList")
    default List<PostcardDto> toPostcardDtoList(List<Postcard> source) {
        return source
                .stream()
                .map(this::toDto)
                .peek(dto -> dto.setUser(null))
                .collect(Collectors.toList());
    }
}
