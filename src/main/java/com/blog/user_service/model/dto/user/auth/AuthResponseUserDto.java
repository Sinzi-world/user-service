package com.blog.user_service.model.dto.user.auth;


import com.blog.user_service.model.dto.user.RegUserResponseDto;
import com.blog.user_service.model.entity.user.UserRoles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект ответа на регистрацию(создание) пользователя")
public class AuthResponseUserDto{

    @Schema(description = "информация о пользователе(краткая)")
    RegUserResponseDto user;

    @Schema(description = "Список ролей пользователя")
    private Set<UserRoles> roles;

    @Schema(description = "Первичный токен JWT")
    private String accessToken;

    @Schema(description = "Обновлённый токен JWT")
    private String refreshToken;

}
