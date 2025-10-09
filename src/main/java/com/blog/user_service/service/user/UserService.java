package com.blog.user_service.service.user;

import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto);

    UserResponseDto getUserById(Long userId);

    UserResponseDto getUserByUsername(String username);

    List<UserResponseDto> getAllUsers();

    Long countAllUsers();

//    void deleteUserById(Long userId);
}
