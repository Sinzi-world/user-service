package com.blog.user_service.controller.comment;


import com.blog.user_service.model.dto.comment.CommentDto;
import com.blog.user_service.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "CommentController", description = "API для работы с комментариями")
public class CommentController {

    private final CommentServiceImpl commentService;

    @Operation(
            summary = "Создать комментарий",
            description = "Создаёт новый комментарий для указанного пользователя и поста"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Комментарий успешно создан",
                    content = @Content(schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "403", description = "Отказано в доступе"),
            @ApiResponse(responseCode = "404", description = "Пользователь или пост не найдены")
    })
    @PostMapping
    public CommentDto createComment(
            @Parameter(description = "ID пользователя, оставляющего комментарий", required = true)
            @RequestParam Long userId,
            @Parameter(description = "ID поста, к которому создаётся комментарий", required = true)
            @RequestParam Long postId,
            @RequestBody CommentDto commentDto) {
        return commentService.createComment(userId, postId, commentDto);
    }

    @Operation(
            summary = "Получить комментарии пользователя",
            description = "Возвращает список комментариев, оставленных указанным пользователем"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Комментарии успешно получены",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommentDto.class)))),
            @ApiResponse(responseCode = "403", description = "Отказано в доступе"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{userId}")
    public List<CommentDto> getCommentsByUserId(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable Long userId) {
        return commentService.getCommentsByUserId(userId);
    }
}
