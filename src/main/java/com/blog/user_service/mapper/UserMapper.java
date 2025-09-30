package com.blog.user_service.mapper;

import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;
import com.blog.user_service.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id",  ignore = true)
    User toUserEntity(CreateUserDto createUserDto);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResponseDto toUserDto(User user);


    void update(UpdateUserDto updateUserDto,@MappingTarget User user);

}
