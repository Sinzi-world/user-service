package com.blog.user_service.service;

import com.blog.user_service.mapper.UserMapper;
import com.blog.user_service.model.dto.user.CreateUserDto;
import com.blog.user_service.model.dto.user.UpdateUserDto;
import com.blog.user_service.model.dto.user.UserDto;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.impl.UserServiceImpl;
import com.blog.user_service.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

    private CreateUserDto createUserDto;
    private User userEntity;
    private UserDto userDto;

    @BeforeEach
    void setUp() {

        createUserDto = CreateUserDto.builder()
                .username("Alex")
                .password("secret123")
                .email("alex@example.com")
                .build();

        userEntity = User.builder()
                .id(1L)
                .username("Alex")
                .password("secret123")
                .email("alex@example.com")
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .username("Alex")
                .email("alex@example.com")
                .build();
    }

    @Test
    public void testUserCreate() {

        when(userMapper.toUserEntity(createUserDto)).thenReturn(userEntity);
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.createUser(createUserDto);

        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getEmail(), result.getEmail());

        verify(userMapper, times(1)).toUserEntity(createUserDto);
        verify(userMapper, times(1)).toUserDto(userEntity);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(createUserDto.getUsername(), capturedUser.getUsername());
        assertEquals(createUserDto.getEmail(), capturedUser.getEmail());
        assertEquals(createUserDto.getPassword(), capturedUser.getPassword());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateUser_Success() {

        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUsername("UpdatedAlex");
        updateUserDto.setEmail("updated@example.com");

        when(userValidator.checkUserExists(
                any(Supplier.class),
                eq("Пользователь с ID: " + userEntity.getId() + " не найден")
        )).thenReturn(userEntity);

        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.updateUser(userEntity.getId(), updateUserDto);

        assertNotNull(result);
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getEmail(), result.getEmail());

        verify(userValidator, times(1))
                .checkUserExists(any(Supplier.class), eq("Пользователь с ID: " + userEntity.getId() + " не найден"));
        verify(userMapper, times(1)).update(updateUserDto, userEntity);
        verify(userMapper, times(1)).toUserDto(userEntity);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateUser_UserNotFound() {

        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setUsername("UpdatedAlex");
        updateUserDto.setEmail("updated@example.com");

        when(userValidator.checkUserExists(
                any(Supplier.class),
                eq("Пользователь с ID: " + userEntity.getId() + " не найден")
        )).thenThrow(new IllegalArgumentException("Пользователь с ID: " + userEntity.getId() + " не найден"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                userService.updateUser(userEntity.getId(), updateUserDto));

        assertEquals("Пользователь с ID: " + userEntity.getId() + " не найден", exception.getMessage());

        verify(userMapper, never()).update(any(), any());
        verify(userMapper, never()).toUserDto(any());
    }

    @Test
    public void testGetUserById_Success() {
        when(userValidator.checkUserExists(
                ArgumentMatchers.<Supplier<Optional<User>>>any(), anyString()))
                .thenReturn(userEntity);
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getEmail(), result.getEmail());

        verify(userValidator, times(1))
                .checkUserExists(ArgumentMatchers.<Supplier<Optional<User>>>any(), anyString());
        verify(userMapper, times(1)).toUserDto(userEntity);
    }

    @Test
    public void testGetUserByUsername_Success() {
        when(userValidator.checkUserExists(
                ArgumentMatchers.<Supplier<Optional<User>>>any(), anyString()))
                .thenReturn(userEntity);
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.getUserByUsername("Alex");

        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getEmail(), result.getEmail());

        verify(userValidator, times(1))
                .checkUserExists(ArgumentMatchers.<Supplier<Optional<User>>>any(), anyString());
        verify(userMapper, times(1)).toUserDto(userEntity);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = List.of(userEntity);
        List<UserDto> dtos = List.of(userDto);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userDto.getUsername(), result.get(0).getUsername());

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toUserDto(userEntity);
    }

    @Test
    public void testCountAllUsers() {
        when(userRepository.count()).thenReturn(5L);

        Long count = userService.countAllUsers();

        assertEquals(5L, count);
        verify(userRepository, times(1)).count();
    }
}