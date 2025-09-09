package com.blog.user_service.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Объект отображения пользователя")
public class UserDto {

    @Schema(description = "уникальный идентификатор пользователя")
    private Long id;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Электронный почтовый ящик")
    private String email;

    @Schema(description = "Номер телефона")
    private String phone;

    @Schema(description = "Город проживания")
    private String city;

    @Schema(description = "Страна проживания")
    private String country;

    @Schema(description = "Опыт работы в годах")
    private Integer experience;

    @Schema(description = "Информация о себе")
    private String aboutMe;

    @Schema(description = "Число подписчиков")
    private Integer followersCount;

    @Schema(description = "Число подписок")
    private Integer followeesCount;

    @Schema(description = "Время создания")
    private String createdAt;

    @Schema(description = "Время обновления")
    private String updatedAt;
}
