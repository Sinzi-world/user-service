package com.blog.user_service.service.impl;

import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.repository.subscription.SubscriptionRepository;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.user.UserService;
import com.blog.user_service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
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
    private final UserValidator userValidator;
    private final SubscriptionRepository subscriptionRepository;

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
        return mapUserToDtoWithCounts(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByUsername(String username) {
        User user = userValidator.checkUserExists(
                () -> userRepository.findByUsername(username),
                "Пользователь с никнеймом " + username + " не найден"
        );
        return mapUserToDtoWithCounts(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapUserToDtoWithCounts)
                .collect(Collectors.toList());
    }


    private UserResponseDto mapUserToDtoWithCounts(User user) {
        long followersCount = subscriptionRepository.countByFolloweeId(user.getId());
        long followeesCount = subscriptionRepository.countByFollowerId(user.getId());

        UserResponseDto dto = userMapper.toUserDto(user);
        dto.setFollowersCount((int) followersCount);
        dto.setFolloweesCount((int) followeesCount);

        return dto;
    }


    @Override
    @Transactional(readOnly = true)
    public Long countAllUsers() {
        return userRepository.count();
    }

    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
