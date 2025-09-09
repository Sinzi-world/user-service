package com.blog.user_service.service.comment;

import com.blog.user_service.model.dto.comment.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto createComment(Long userId, Long postId, CommentDto commentDto);

    CommentDto updateComment(CommentDto commentDto);

    List<CommentDto> getCommentsByUserId(Long userId);

    List<CommentDto> getCommentsByPostId(Long postId);


}
