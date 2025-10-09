package com.blog.user_service.controller.auth;


import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.auth.AuthRequestUserDto;
import com.blog.user_service.model.dto.user.auth.AuthResponseUserDto;
import com.blog.user_service.model.dto.user.auth.ChangePasswordDto;
import com.blog.user_service.service.user.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "API для аутентификации пользователя")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Создать пользователя",
            description = "Создаёт нового пользователя в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(schema = @Schema(implementation = AuthResponseUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email или username уже существует")
    })
    @PostMapping("/registration")
    public AuthResponseUserDto registerUser(@RequestBody @Valid CreateUserDto dto) {
        return authService.registerUser(dto);
    }


    @Operation(
            summary = "Аутентифицировать пользователя",
            description = "Позволяет пользователю залогиниться в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(schema = @Schema(implementation = AuthResponseUserDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email или username уже существует")
    })
    @PostMapping("/login")
    public AuthResponseUserDto login(@RequestBody AuthRequestUserDto authRequestUserDto){
        return authService.login(authRequestUserDto);
    }

    @Operation(
            summary = "Сменить пароль авторизованного пользователя",
            description = "Позволяет сменить пароль пользователя, аутентифицированного системой"
    )
    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    @PostMapping("/reset-password")
    public String changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return authService.changePassword(changePasswordDto);
    }
}
