package com.blog.user_service.controller.post;

import com.blog.user_service.dto.post.PostDto;
import com.blog.user_service.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/author/{authorId}")
    public PostDto createPost(@PathVariable Long authorId, @RequestBody PostDto postDto){
        return postService.createPost(authorId, postDto);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

}
