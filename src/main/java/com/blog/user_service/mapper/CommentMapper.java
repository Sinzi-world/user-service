package com.blog.user_service.mapper;

import com.blog.user_service.dto.comment.CommentDto;
import com.blog.user_service.entity.comment.Comment;
import com.blog.user_service.entity.post.Post;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = UserMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user", target = "user")
    CommentDto toDto(Comment comment);

    @Mapping(target = "post", ignore = true)
    @Mapping(source = "user", target = "user")
    Comment toEntity(CommentDto commentDto);

    @AfterMapping
    default void fillPost(CommentDto dto, @MappingTarget Comment comment) {
        if (dto.getPostId() != 0) {
            Post post = new Post();
            post.setId(dto.getPostId());
            comment.setPost(post);
        }
    }
}

