package com.blog.user_service.service;

import com.blog.user_service.dto.user.UpdateUserDto;
import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.dto.user.CreateUserDto;
import com.blog.user_service.dto.user.UserDto;
import com.blog.user_service.entity.User;
import com.blog.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.toUserEntity(createUserDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public UserDto updateUser(Long userId, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с ID: " + userId + " не найден");
                });
        userMapper.update(updateUserDto, user);
        return userMapper.toUserDto(user);
    }

    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с ID: " + userId + " не найден");
                        });
    }

    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с никнеймом " + username + " не найден");
                });
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public Long countAllUsers() {
        return userRepository.count();
    }

    public void deleteUserById(Long userId) {
        if(userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else  {
            log.debug("User not found");
            throw new IllegalArgumentException("Пользователь с ID: " + userId + " не найден");
        }
    }

}
