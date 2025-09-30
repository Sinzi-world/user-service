package com.blog.user_service.controller.user;

import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserResponseDto;
import com.blog.user_service.service.impl.UserServiceImpl;
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

    private final UserServiceImpl userService;

    @Operation(
            summary = "Создать пользователя",
            description = "Создаёт нового пользователя в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким email или username уже существует")
    })
    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody @Valid CreateUserDto dto) {
        return userService.registerUser(dto);
    }

    @Operation(
            summary = "Обновить пользователя",
            description = "Обновляет данные пользователя по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
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
    @GetMapping("/list")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Получить количество пользователей",
            description = "Возвращает общее количество пользователей в системе"
    )
    @GetMapping("/count")
    public Long countAllUsers() {
        return userService.countAllUsers();
    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//    }
}

