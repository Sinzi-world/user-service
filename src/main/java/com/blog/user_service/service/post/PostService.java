package com.blog.user_service.service.post;

import com.blog.user_service.dto.post.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(Long authorId, PostDto postDto);

    PostDto updatePost(Long postId, PostDto postDto);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPosts();

    List<PostDto> getPostsByAuthorId(Long authorId);

    void deletePostById(Long postId);


}
