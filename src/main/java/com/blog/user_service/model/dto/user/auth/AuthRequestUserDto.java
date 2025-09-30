package com.blog.user_service.model.dto.user.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект запроса на регистрацию(создание) пользователя")
public class AuthRequestUserDto{

    @NotBlank
    @Size(max = 64)
    @Schema(description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "Пароль", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
