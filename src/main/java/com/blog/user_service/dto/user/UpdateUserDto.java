package com.blog.user_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @Size(max = 64)
    private String username;

    @Email
    @Size(max = 64)
    private String email;

    @Size(max = 32)
    private String phone;

    private String city;

    private Integer experience;
}
