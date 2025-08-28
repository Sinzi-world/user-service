package com.blog.user_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank
    @Size(max = 64)
    private String username;

    @NotBlank
    @Email
    @Size(max = 64)
    private String email;

    @Size(max = 32)
    private String phone;

    @NotBlank
    @Size(max = 128)
    private String password;

    private String city;

    private String country;

    private Integer experience;

    private String aboutMe;
}
