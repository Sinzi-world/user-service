package com.blog.user_service.mapper;

import com.blog.user_service.dto.blog.AuthorInfo;
import com.blog.user_service.dto.blog.post.PostDto;
import com.blog.user_service.entity.post.Post;
import com.blog.user_service.entity.user.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = UserMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Post toEntity(PostDto PostDto);

    @Mapping(target = "author", qualifiedByName = "mapAuthor")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PostDto toDto(Post post);

    @Named("mapAuthor")
    default AuthorInfo mapAuthor(User user) {
        if (user == null) return null;
        return new AuthorInfo(user.getId(), user.getUsername());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updatePost(PostDto postDto, @MappingTarget Post post);
}
