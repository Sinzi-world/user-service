package com.blog.user_service.dto.comment;

import com.blog.user_service.dto.post.AuthorInfo;
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

    private AuthorInfo user;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
