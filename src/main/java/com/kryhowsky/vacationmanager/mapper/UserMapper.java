package com.kryhowsky.vacationmanager.mapper;

import com.kryhowsky.vacationmanager.model.User;
import com.kryhowsky.vacationmanager.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
