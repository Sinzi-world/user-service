package com.blog.user_service.dto.blog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Объект для краткой информации об авторе поста")
public class AuthorInfo {

    @Schema(description = "Уникальный идентификатор пользователя")
    private long id;

    @Schema(description = "Имя пользователя")
    private String username;
}
