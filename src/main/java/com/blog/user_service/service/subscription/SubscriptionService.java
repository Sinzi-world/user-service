package com.blog.user_service.service.subscription;

import com.blog.user_service.model.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionService {

    void follow(Long followerId, Long followeeId);

    void unfollow(Long followerId, Long followeeId);

    List<User> getFollowers(Long followeeId);

    List<User> getFollowees(Long followerId);

}
