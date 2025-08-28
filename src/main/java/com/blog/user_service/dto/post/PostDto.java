package com.blog.user_service.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;

    private String title;

    private String content;

    private AuthorInfo author;

    private String category;

    private String tags;

    private String createdAt;

    private String updatedAt;
}
