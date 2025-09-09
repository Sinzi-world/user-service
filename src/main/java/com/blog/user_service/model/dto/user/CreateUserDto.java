package com.blog.user_service.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект создания пользователя")
public class CreateUserDto {

    @NotBlank
    @Size(max = 64)
    @Schema(description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @Email
    @Size(max = 64)
    @Schema(description = "Электронный почтовый ящик", example = "example@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Size(max = 32)
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    @Schema(description = "Номер телефона", example = "+79999999999")
    private String phone;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "Пароль", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Город проживания", example = "Москва")
    private String city;

    @Schema(description = "Страна проживания", example = "Россия")
    private String country;

    @Schema(description = "Опыт работы в годах", example = "3")
    private Integer experience;

    @Schema(description = "Информация о себе", example = "Java-разработчик, увлекаюсь спортом")
    private String aboutMe;
}
