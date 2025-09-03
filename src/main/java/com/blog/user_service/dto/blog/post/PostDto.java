package com.blog.user_service.dto.blog.post;

import com.blog.user_service.dto.blog.AuthorInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO поста в блоге")
public class PostDto {

    @Schema(description = "Уникальный идентификатор поста", example = "1")
    private long id;

    @Schema(description = "Заголовок поста", example = "Мой первый пост")
    private String title;

    @Schema(description = "Содержание поста", example = "Это содержимое моего первого поста в приложении.")
    private String content;

    @Schema(description = "Автор поста (id и username)", implementation = AuthorInfo.class)
    private AuthorInfo author;

    @Schema(description = "Категория поста", example = "Программирование")
    private String category;

    @Schema(description = "Список тегов, разделённых запятой", example = "java,spring,backend")
    private String tags;

    @Schema(description = "Дата и время создания поста", example = "2025-08-28 16:49:38")
    private String createdAt;

    @Schema(description = "Дата и время последнего обновления поста", example = "2025-08-28 16:49:38")
    private String updatedAt;
}
