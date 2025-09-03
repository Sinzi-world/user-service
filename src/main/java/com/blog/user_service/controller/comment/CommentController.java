package com.blog.user_service.controller.comment;


import com.blog.user_service.dto.comment.CommentDto;
import com.blog.user_service.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    public CommentDto createComment(@RequestParam Long userId,
                                    @RequestParam Long postId,
                                    @RequestBody CommentDto commentDto){
        return commentService.createComment(userId, postId, commentDto);
    }

    @GetMapping("/{userId}")
    public List<CommentDto> getCommentsByUserId(@PathVariable Long userId){
        return commentService.getCommentsByUserId(userId);
    }
}
