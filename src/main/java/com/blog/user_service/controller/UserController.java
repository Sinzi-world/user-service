package com.blog.user_service.controller;

import com.blog.user_service.dto.*;
import com.blog.user_service.dto.user.CreateUserDto;
import com.blog.user_service.dto.user.UpdateUserDto;
import com.blog.user_service.dto.user.UserDto;
import com.blog.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid CreateUserDto dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody @Valid UpdateUserDto dto) {
        return userService.updateUser(id, dto);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

