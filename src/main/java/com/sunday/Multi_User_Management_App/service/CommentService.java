package com.sunday.Multi_User_Management_App.service;

import com.sunday.Multi_User_Management_App.DTO.request.CommentRequest;
import com.sunday.Multi_User_Management_App.DTO.response.CommentResponse;

public interface CommentService {
    CommentResponse addComment(Long taskId, CommentRequest commentRequest);

    CommentResponse editComment(Long commentId, CommentRequest commentRequest);

    void deleteComment(Long commentId);

    void deleteCommentAsAdmin(Long commentId);
}
