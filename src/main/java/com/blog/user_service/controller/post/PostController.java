package com.blog.user_service.controller.post;

import com.blog.user_service.dto.post.PostDto;
import com.blog.user_service.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/author/{authorId}")
    public PostDto createPost(@PathVariable Long authorId, @RequestBody PostDto postDto){
        return postService.createPost(authorId, postDto);
    }

    @PutMapping("/update/{postId}")
    public PostDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        return postService.updatePost(postId, postDto);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/post-author/{authorId}")
    public List<PostDto> getPostsByAuthorId(@PathVariable Long authorId){
        return postService.getPostsByAuthorId(authorId);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId){
        postService.deletePostById(postId);
    }

}
