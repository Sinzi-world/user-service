package com.blog.user_service.service.user;


import com.blog.user_service.model.dto.user.auth.AuthRequestUserDto;
import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.dto.user.auth.ChangePasswordDto;

public interface AuthService {

    AuthResponseUserDto login(AuthRequestUserDto requestUserDto);

    String changePassword(ChangePasswordDto changePasswordDto);
}
