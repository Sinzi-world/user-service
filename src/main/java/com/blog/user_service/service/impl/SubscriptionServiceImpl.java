package com.blog.user_service.service.impl;

import com.blog.user_service.model.entity.subscription.Subscription;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.repository.subscription.SubscriptionRepository;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.SubscriptionService;
import com.blog.user_service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final UserValidator userValidator;


    @Override
    @Transactional
    public void follow(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("Нельзя подписаться на самого себя");
        }
        if (subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {
            throw new IllegalArgumentException("Данная подписка уже существует");
        }

        User follower = userValidator.checkUserExists(
                () -> userRepository.findById(followerId),
                "Пользователь с ID: " + followerId + " не найден"
        );
        User followee = userValidator.checkUserExists(
                () -> userRepository.findById(followeeId),
                "Пользователь с ID: " + followeeId + " не найден"
        );

        Subscription sub = Subscription.builder()
                .follower(follower)
                .followee(followee)
                .createdAt(LocalDateTime.now())
                .build();

        subscriptionRepository.save(sub);
    }

    @Override
    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("Нельзя отписаться от самого себя");
        }
        if (subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {
            try {
                Subscription subscription = subscriptionRepository
                        .findByFollowerIdAndFolloweeId(followerId, followeeId)
                        .orElseThrow(() -> new IllegalStateException("Вы не подписаны на этого пользователя"));
                subscriptionRepository.delete(subscription);
            } catch (IllegalStateException e) {
                System.out.println("Не удалось отписаться " + e.getMessage());
            } catch (DataAccessException e) {
                throw new RuntimeException("Ошибка при удалении подписки", e);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getFollowers(Long followeeId) {
        return subscriptionRepository.findAllByFolloweeId(followeeId).stream()
                .map(Subscription::getFollower)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getFollowees(Long followerId) {
        return subscriptionRepository.findAllByFollowerId(followerId).stream()
                .map(Subscription::getFollowee)
                .toList();
    }
}
