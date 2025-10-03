package com.blog.user_service.service.impl;

import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.model.entity.user.UserRoles;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.user.UserService;
import com.blog.user_service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    @Transactional
//    public UserResponseDto registerUser(CreateUserDto createUserDto) {
//        User user = userMapper.toUserEntity(createUserDto);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Set.of(UserRoles.ROLE_USER));
//        userRepository.save(user);
//        return userMapper.toUserDto(user);
//    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto) {
        User userById = userValidator.checkUserExists(
                () -> userRepository.findById(userId),
                "Пользователь с ID: " + userId + " не найден"
        );
        userMapper.update(updateUserDto, userById);
        return userMapper.toUserDto(userById);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long userId) {
        User user = userValidator.checkUserExists(
                () -> userRepository.findById(userId),
                "Пользователь с ID: " + userId + " не найден"
        );
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByUsername(String username) {
        User user = userValidator.checkUserExists(
                () -> userRepository.findByUsername(username),
                "Пользователь с никнеймом " + username + " не найден"
        );
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
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
