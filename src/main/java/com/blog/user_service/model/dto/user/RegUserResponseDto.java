package com.blog.user_service.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Короткий объект пользователя для регистрации/логина")
public class RegUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
}
