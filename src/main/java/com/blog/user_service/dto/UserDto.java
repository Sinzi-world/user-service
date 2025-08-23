package com.blog.user_service.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String aboutMe;

    private String country;

    private Integer experience;

    private Integer followersCount;

    private Integer followeesCount;
}
