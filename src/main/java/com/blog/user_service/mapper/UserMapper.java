package com.blog.user_service.mapper;

import com.blog.user_service.dto.user.CreateUserDto;
import com.blog.user_service.dto.user.UpdateUserDto;
import com.blog.user_service.dto.user.UserDto;
import com.blog.user_service.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id",  ignore = true)
    User toUserEntity(CreateUserDto createUserDto);

    void update(UpdateUserDto updateUserDto,@MappingTarget User user);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserDto toUserDto(User user);


}
