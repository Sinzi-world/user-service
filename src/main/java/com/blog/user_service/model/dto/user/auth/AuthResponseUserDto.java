package com.blog.user_service.model.dto.user.auth;


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

    @Schema(description = "уникальный идентификатор пользователя")
    private Long id;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Электронный почтовый ящик")
    private String email;

    @Schema(description = "Список ролей пользователя")
    private Set<UserRoles> roles;

    @Schema(description = "Токен JWT")
    private String token;

}
