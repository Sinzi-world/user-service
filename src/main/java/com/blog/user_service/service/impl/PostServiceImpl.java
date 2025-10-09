package com.blog.user_service.service.impl;

import com.blog.user_service.model.dto.post.PostDto;
import com.blog.user_service.model.entity.post.Post;
import com.blog.user_service.model.entity.user.User;
import com.blog.user_service.mapper.PostMapper;
import com.blog.user_service.repository.post.PostRepository;
import com.blog.user_service.repository.user.UserRepository;
import com.blog.user_service.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;


    @Override
    @Transactional
    public PostDto createPost(Long authorId, PostDto postDto){
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.debug("No author found with this id {}", authorId);
                    return new IllegalArgumentException("No author found with this id " + authorId);
                });
        Post post = postMapper.toEntity(postDto);
        post.setAuthor(author);

        postRepository.save(post);
        return postMapper.toDto(post);
    }


    @Override
    @Transactional
    public PostDto updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("No post found with id " + postId));

        postMapper.updatePost(postDto, post);

        post.setUpdatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDto getPostById(Long postId) {
        return postRepository.findById(postId)
                .map(postMapper::toDto)
                .orElseThrow(() -> {
                    log.debug("No post found with id {}", postId);
                    return new IllegalArgumentException("No post found with id " + postId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsByAuthorId(Long authorId) {
        return postRepository.findAllByAuthorId(authorId)
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
