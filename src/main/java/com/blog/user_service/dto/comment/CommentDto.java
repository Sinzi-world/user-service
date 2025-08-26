package com.blog.user_service.dto.comment;

import com.blog.user_service.dto.user.UserDto;
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

    private long id;

    private long postId;

    private UserDto user;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
