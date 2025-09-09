package com.blog.user_service.controller.post;

import com.blog.user_service.model.dto.post.PostDto;
import com.blog.user_service.service.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "PostController", description = "API для работы с постами")
public class PostController {

    private final PostServiceImpl postService;

    @Operation(
            summary = "Создать пост",
            description = "Создаёт новый пост для указанного автора"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пост успешно создан",
                    content = @Content(schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "404", description = "Автор не найден")
    })
    @PostMapping("/author/{authorId}")
    public PostDto createPost(
            @Parameter(description = "ID автора", required = true)
            @PathVariable Long authorId,
            @RequestBody PostDto postDto) {
        return postService.createPost(authorId, postDto);
    }

    @Operation(
            summary = "Обновить пост",
            description = "Обновляет пост по его ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пост успешно обновлён",
                    content = @Content(schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "404", description = "Пост не найден")
    })
    @PutMapping("/update/{postId}")
    public PostDto updatePost(
            @Parameter(description = "ID поста", required = true)
            @PathVariable Long postId,
            @RequestBody PostDto postDto) {
        return postService.updatePost(postId, postDto);
    }

    @Operation(
            summary = "Получить пост по ID",
            description = "Возвращает информацию о посте по его идентификатору"
    )
    @GetMapping("/{postId}")
    public PostDto getPostById(
            @Parameter(description = "ID поста", required = true)
            @PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @Operation(
            summary = "Получить список всех постов",
            description = "Возвращает список всех постов в системе"
    )
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @Operation(
            summary = "Получить посты автора",
            description = "Возвращает список постов указанного автора по его ID"
    )
    @GetMapping("/post-author/{authorId}")
    public List<PostDto> getPostsByAuthorId(
            @Parameter(description = "ID автора", required = true)
            @PathVariable Long authorId) {
        return postService.getPostsByAuthorId(authorId);
    }

    //    @DeleteMapping("/{postId}")
//    public void deletePostById(@PathVariable Long postId){
//        postService.deletePostById(postId);
//    }

}
