package com.blog.user_service.service.user;

import com.blog.user_service.dto.user.CreateUserDto;
import com.blog.user_service.dto.user.UpdateUserDto;
import com.blog.user_service.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserDto createUserDto);

    UserDto updateUser(Long userId, UpdateUserDto updateUserDto);

    UserDto getUserById(Long userId);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    Long countAllUsers();

    void deleteUserById(Long userId);
}
