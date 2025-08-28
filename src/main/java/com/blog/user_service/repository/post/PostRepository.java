package com.blog.user_service.repository.post;

import com.blog.user_service.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.id = :authorId")
    List<Post> findAllByAuthorId(@Param("authorId") Long authorId);
}
