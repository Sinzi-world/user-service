package com.blog.user_service.service.impl;

import com.blog.user_service.dto.user.UpdateUserDto;
import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.dto.user.CreateUserDto;
import com.blog.user_service.dto.user.UserDto;
import com.blog.user_service.entity.user.User;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.toUserEntity(createUserDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с ID: " + userId + " не найден");
                });
        userMapper.update(updateUserDto, user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с ID: " + userId + " не найден");
                });
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException("Пользователь с никнеймом " + username + " не найден");
                });
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countAllUsers() {
        return userRepository.count();
    }

//    @Transactional
//    public void deleteUserById(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("No user with id " + userId));
//
//        user.getFollowees().clear();
//        user.getFollowers().clear();
//
//        user.getPosts().forEach(post -> post.setAuthor(null));
//
//        userRepository.delete(user);
//    }

}
