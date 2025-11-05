package com.blog.user_service.service.impl;

import com.blog.user_service.kafka.producer.KafkaProducer;
import com.blog.user_service.model.dto.subscription.UserSubscriptionEvent;
import com.blog.user_service.model.entity.subscription.Subscription;
import com.blog.user_service.model.entity.subscription.SubscriptionAction;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.repository.subscription.SubscriptionRepository;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.subscription.SubscriptionService;
import com.blog.user_service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final KafkaProducer kafkaProducer;


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

        UserSubscriptionEvent message = UserSubscriptionEvent.builder()
                .followerId(followerId)
                .followeeId(followeeId)
                .timestamp(LocalDateTime.now())
                .action(SubscriptionAction.FOLLOW)
                .build();

        kafkaProducer.sendMessage("analytics-topic",message);
        kafkaProducer.sendMessage("notification-topic", message);

    }

    @Override
    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("Нельзя отписаться от самого себя");
        }

        Subscription subscription = subscriptionRepository
                .findByFollowerIdAndFolloweeId(followerId, followeeId)
                .orElseThrow(() -> new IllegalArgumentException("Вы не подписаны на этого пользователя"));

        subscriptionRepository.delete(subscription);

        UserSubscriptionEvent message = UserSubscriptionEvent.builder()
                .followerId(followerId)
                .followeeId(followeeId)
                .timestamp(LocalDateTime.now())
                .action(SubscriptionAction.UNFOLLOW)
                .build();

        kafkaProducer.sendMessage("analytics-topic", message);
        kafkaProducer.sendMessage("notification-topic", message);
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
