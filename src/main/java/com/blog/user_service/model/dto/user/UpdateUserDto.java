package com.blog.user_service.model.dto.user;

import com.blog.user_service.model.entity.user.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект обновления информации о пользователе")
public class UpdateUserDto {

    @Size(max = 64)
    @Schema(description = "Имя пользователя", example = "Alexander")
    private String username;

    @Email
    @Size(max = 64)
    @Schema(description = "Электронный почтовый ящик", example = "example@example.com")
    private String email;

    @Size(max = 32)
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    @Schema(description = "Номер телефона", example = "+79999999999")
    private String phone;

    @Schema(description = "Город проживания", example = "Москва")
    private String city;

    @Schema(description = "Страна проживания", example = "Россия")
    private String country;

    @Schema(description = "Опыт работы в годах", example = "3")
    private Integer experience;

    @Schema(description = "Информация о себе", example = "Java-разработчик, увлекаюсь спортом")
    private String aboutMe;
}
