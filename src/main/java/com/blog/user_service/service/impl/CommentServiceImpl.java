package com.blog.user_service.service.impl;

import com.blog.user_service.dto.blog.comment.CommentDto;
import com.blog.user_service.entity.comment.Comment;
import com.blog.user_service.entity.post.Post;
import com.blog.user_service.entity.user.User;
import com.blog.user_service.mapper.CommentMapper;
import com.blog.user_service.repository.comment.CommentRepository;
import com.blog.user_service.repository.post.PostRepository;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.comment.CommentService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@Builder
@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto createComment(Long userId, Long postId, CommentDto commentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.debug("Author not found");
                    return new IllegalArgumentException("Author with id " + userId + " not found");
                });
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.debug("Post not found");
                    return new IllegalArgumentException("Post with id " + postId + " not found");
                });

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentsByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        List<Comment> comments = commentRepository.findAllByUserId(userId);

        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return List.of();
    }
}
