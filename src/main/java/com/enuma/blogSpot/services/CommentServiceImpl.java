package com.enuma.blogSpot.services;

import com.enuma.blogSpot.payload.request.CommentRequest;

import java.util.List;

public interface CommentServiceImpl {
    CommentRequest createComment(Long postId, CommentRequest commentRequest);


    List<CommentRequest> getCommentsByPostId(Long postId);


    CommentRequest getCommentById(Long postId, Long commentId);
    CommentRequest updateComment(Long postId, Long commentId, CommentRequest commentRequest);

    void deleteComment(Long postId, Long commentId);
}
