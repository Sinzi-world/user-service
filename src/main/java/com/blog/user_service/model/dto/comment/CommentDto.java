package com.blog.user_service.model.dto.comment;

import com.blog.user_service.model.dto.AuthorInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO комментария к посту")
public class CommentDto {

    @Schema(description = "Уникальный идентификатор комментария", example = "1")
    private Long id;

    @Schema(description = "Идентификатор поста, к которому относится комментарий", example = "10")
    private Long postId;

    @Schema(description = "Автор комментария (id и username)", implementation = AuthorInfo.class)
    private AuthorInfo user;

    @Schema(description = "Содержимое комментария", example = "Отличная статья!")
    private String content;

    @Schema(description = "Дата и время создания комментария", example = "2025-09-03T14:20:00")
    private LocalDateTime createdAt;

    @Schema(description = "Дата и время последнего обновления комментария", example = "2025-09-03T14:25:00")
    private LocalDateTime updatedAt;
}
