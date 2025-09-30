package com.blog.user_service.mapper;


import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthResponseUserDto toUserDto(User user);
}