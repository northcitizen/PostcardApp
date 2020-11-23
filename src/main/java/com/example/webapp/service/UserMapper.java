package com.example.webapp.service;

import com.example.webapp.dto.UserDto;
import com.example.webapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PostcardMapper.class)
public interface UserMapper {
    @Mapping(target = "postcards", source = "postcards", qualifiedByName = "postcardDTOList")
    UserDto toDto(User user);
}
