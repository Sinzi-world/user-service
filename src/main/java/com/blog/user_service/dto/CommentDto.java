package com.blog.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    private Long postId;

    private UserDto user;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
