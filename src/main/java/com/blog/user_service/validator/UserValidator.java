package com.blog.user_service.validator;

import com.blog.user_service.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserValidator {

    /**
     * Универсальный метод проверки существования пользователя.
     *
     * @param finder функция, которая возвращает Optional<User>
     * @param errorMessage сообщение об ошибке, если пользователь не найден
     * @return найденный User
     */
    public User checkUserExists(Supplier<Optional<User>> finder, String errorMessage) {
        return finder.get()
                .orElseThrow(() -> {
                    log.debug("User not found");
                    return new IllegalArgumentException(errorMessage);
                });
    }
}

