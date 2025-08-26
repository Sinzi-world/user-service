package com.blog.user_service.repository.post;

import com.blog.user_service.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {
}
