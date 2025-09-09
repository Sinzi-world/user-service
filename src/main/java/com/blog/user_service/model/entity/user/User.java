package com.blog.user_service.model.entity.user;

import com.blog.user_service.model.entity.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false, unique = true)
    private String username;

    @Column(length = 64, nullable = false, unique = true)
    private String email;

    @Column(length = 32, unique = true)
    private String phone;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active = true;

    @Column(length = 4096)
    private String aboutMe;

    @Column(length = 64)
    private String country;

    @Column(length = 64)
    private String city;

    private Integer experience;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    // Пользователи, на которых я подписан
    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id")
    )
    private List<User> followees;

    // Пользователи, которые подписаны на меня
    @ManyToMany(mappedBy = "followees")
    private List<User> followers;
}
