package com.blog.user_service.model.dto.user.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект запроса смены пароля")
public class ChangePasswordDto {

    @Schema(description = "Текущий пароль")
    private String oldPassword;

    @Schema(description = "Новый пароль")
    private String newPassword;
}
