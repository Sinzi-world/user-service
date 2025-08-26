package com.blog.user_service.mapper;

import com.blog.user_service.dto.post.PostDto;
import com.blog.user_service.entity.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = UserMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {


    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);
}
