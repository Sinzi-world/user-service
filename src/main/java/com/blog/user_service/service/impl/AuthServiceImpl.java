package com.blog.user_service.service.impl;

import com.blog.user_service.mapper.AuthMapper;
import com.blog.user_service.model.dto.user.auth.AuthRequestUserDto;
import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.dto.user.auth.ChangePasswordDto;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.user.AuthService;
import com.blog.user_service.validator.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;


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

        return authMapper.toUserDto(user);
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
