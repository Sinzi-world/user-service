package com.blog.user_service.model.dto.subscription;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сообщение в kafka о подписке/отписке пользователей")
public class UserSubscriptionEvent {
    @Schema(description = "Уникальный идентификатор пользователя, который подписался/отписался", example = "1")
    private Long followerId;

    @Schema(description = "Уникальный идентификатор пользователя, на которого подписались/отписались", example = "1")
    private Long followeeId;

    @Schema(description = "Время подписки/отписки")
    private LocalDateTime timestamp;

    @Schema(description = "статус", example = "FOLLOW/UNFOLLOW")
    private String action;
}

