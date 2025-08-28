package com.blog.user_service.mapper;

import com.blog.user_service.dto.post.PostDto;
import com.blog.user_service.entity.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = UserMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Post toEntity(PostDto PostDto);

    @Mapping(source = "author", target = "author")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PostDto toDto(Post post);

    void updatePost(PostDto postDto, @MappingTarget Post post);
}
