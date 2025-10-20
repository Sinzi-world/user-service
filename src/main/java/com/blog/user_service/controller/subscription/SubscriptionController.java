package com.blog.user_service.controller.subscription;

import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.service.SubscriptionService;
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
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "SubscriptionController", description = "API для работы с подписками пользователей")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Подписаться на пользователя",
            description = "Создаёт подписку текущего пользователя на другого пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Подписка успешно создана"),
            @ApiResponse(responseCode = "400", description = "Нельзя подписаться на самого себя или подписка уже существует"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PostMapping("/follow")
    public void follow(
            @Parameter(description = "ID пользователя, который подписывается", required = true)
            @RequestParam Long followerId,
            @Parameter(description = "ID пользователя, на которого подписываются", required = true)
            @RequestParam Long followeeId) {
        subscriptionService.follow(followerId, followeeId);
    }

    @Operation(
            summary = "Отписаться от пользователя",
            description = "Удаляет подписку текущего пользователя на другого пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Подписка успешно удалена"),
            @ApiResponse(responseCode = "400", description = "Нельзя отписаться от самого себя"),
            @ApiResponse(responseCode = "404", description = "Подписка не найдена")
    })
    @DeleteMapping("/unfollow")
    public void unfollow(
            @Parameter(description = "ID пользователя, который отписывается", required = true)
            @RequestParam Long followerId,
            @Parameter(description = "ID пользователя, от которого отписываются", required = true)
            @RequestParam Long followeeId) {
        subscriptionService.unfollow(followerId, followeeId);
    }

    @Operation(
            summary = "Получить список подписчиков пользователя",
            description = "Возвращает список пользователей, которые подписаны на данного пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список подписчиков успешно получен",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{userId}/followers")
    public List<User> getFollowers(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable Long userId) {
        return subscriptionService.getFollowers(userId);
    }

    @Operation(
            summary = "Получить список подписок пользователя",
            description = "Возвращает список пользователей, на которых подписан данный пользователь"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список подписок успешно получен",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{userId}/followees")
    public List<User> getFollowees(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable Long userId) {
        return subscriptionService.getFollowees(userId);
    }
}
