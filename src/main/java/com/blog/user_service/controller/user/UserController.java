package com.blog.user_service.controller.user;

import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;
import com.blog.user_service.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "API для работы с пользователями")
public class UserController {

    private final UserService userService;


    @Operation(
            summary = "Обновить пользователя",
            description = "Обновляет данные пользователя по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Отказано в доступе"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserDto dto) {
        return userService.updateUser(id, dto);
    }

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает информацию о пользователе по его идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Отказано в доступе"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public UserResponseDto getUser(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Получить пользователя по username",
            description = "Возвращает информацию о пользователе по его имени"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Отказано в доступе"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/username/{username}")
    public UserResponseDto getUserByUsername(
            @Parameter(description = "Имя пользователя", required = true, example = "alex123")
            @PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @Operation(
            summary = "Получить список всех пользователей",
            description = "Возвращает список всех зарегистрированных пользователей"
    )
    @ApiResponse(responseCode = "403", description = "Отказано в доступе")
    @GetMapping("/list")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Получить количество пользователей",
            description = "Возвращает общее количество пользователей в системе"
    )
    @ApiResponse(responseCode = "403", description = "Отказано в доступе")
    @GetMapping("/count")
    public Long countAllUsers() {
        return userService.countAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}

