package com.blog.user_service.repository;

import com.blog.user_service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {}
