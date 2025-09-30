package com.blog.user_service.controller.user;


import com.blog.user_service.model.dto.user.auth.AuthRequestUserDto;
import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.dto.user.auth.ChangePasswordDto;
import com.blog.user_service.service.user.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "API для аутентификации пользователя")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseUserDto login(@RequestBody AuthRequestUserDto authRequestUserDto){
        return authService.login(authRequestUserDto);
    }

    @PostMapping("/reset-password")
    public String changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return authService.changePassword(changePasswordDto);
    }
}
