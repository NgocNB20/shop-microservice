package com.programing.tech.mapper;


import com.programing.tech.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
}
