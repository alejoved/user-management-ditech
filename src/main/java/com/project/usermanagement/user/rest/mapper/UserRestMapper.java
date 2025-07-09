package com.project.usermanagement.user.rest.mapper;

import com.project.usermanagement.user.domain.models.User;
import com.project.usermanagement.user.rest.dto.UserDto;
import com.project.usermanagement.user.rest.dto.UserResponseDto;

public class UserRestMapper {
    public static User toDomain(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());
        return user;

    }
    public static UserResponseDto toDTO(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setActive(user.isActive());
        return userResponseDto;
    }    
}
