package com.blog.user_service.dto.post;

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
public class PostDto {

    private long id;

    private String title;

    private String content;

    private UserDto author;

    private String category;

    private String tags;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
