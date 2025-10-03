package com.blog.user_service.service.impl;

import com.blog.user_service.mapper.AuthMapper;
import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.auth.AuthRequestUserDto;
import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.dto.user.auth.ChangePasswordDto;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.model.entity.user.UserRoles;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.security.CustomUserDetails;
import com.blog.user_service.security.service.JwtService;
import com.blog.user_service.service.user.AuthService;
import com.blog.user_service.validator.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public AuthResponseUserDto registerUser(CreateUserDto createUserDto) {
        User user = userMapper.toUserEntity(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(UserRoles.ROLE_USER));
        userRepository.save(user);
        String token = jwtService.generateToken(new CustomUserDetails(user));
        AuthResponseUserDto responseDto = authMapper.toUserDto(user);
        responseDto.setToken(token);
        return responseDto;
    }

    @Override
    @Transactional
    public AuthResponseUserDto login(AuthRequestUserDto requestUserDto) {
        String username = requestUserDto.getUsername();
        User user = userValidator.checkUserExists(() ->
                userRepository.findByUsername(username),
                "Пользователь с никнеймом " + username + " не найден"
        );

        if (!passwordEncoder.matches(requestUserDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Неверный пароль");
        }

        String token = jwtService.generateToken(new CustomUserDetails(user));

        AuthResponseUserDto responseDto = authMapper.toUserDto(user);

        responseDto.setToken(token);

        return responseDto;
    }

    @Override
    @Transactional
    public String changePassword(ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Ошибка аутентификации");
        }

        String username = authentication.getName();

        User user = userValidator.checkUserExists(() ->
                userRepository.findByUsername(username),
                "Пользователь не найден");

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Неверно введён текущий пароль");
        }

        if (changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())) {
            throw new IllegalArgumentException("Новый и старый пароли должны отличаться");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
        return "Успешная смена пароля.";
    }
}
