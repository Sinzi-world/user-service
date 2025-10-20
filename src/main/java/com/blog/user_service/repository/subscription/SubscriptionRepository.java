package com.blog.user_service.repository.subscription;

import com.blog.user_service.model.entity.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository("subscriptionRepository")
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    Optional<Subscription> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    List<Subscription> findAllByFollowerId(Long followerId);

    List<Subscription> findAllByFolloweeId(Long followeeId);

    long countByFolloweeId(Long followeeId);
    long countByFollowerId(Long followerId);
}
