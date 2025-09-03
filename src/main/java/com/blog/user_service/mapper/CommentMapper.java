package com.blog.user_service.mapper;

import com.blog.user_service.dto.blog.comment.CommentDto;
import com.blog.user_service.entity.comment.Comment;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = UserMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(source = "user", target = "user")
    Comment toEntity(CommentDto commentDto);

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user", target = "user")
    CommentDto toDto(Comment comment);
}

