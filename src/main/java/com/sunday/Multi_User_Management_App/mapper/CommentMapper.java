package com.sunday.Multi_User_Management_App.mapper;

import com.sunday.Multi_User_Management_App.DTO.request.CommentRequest;
import com.sunday.Multi_User_Management_App.DTO.response.CommentResponse;
import com.sunday.Multi_User_Management_App.model.Comment;
import com.sunday.Multi_User_Management_App.model.Task;
import com.sunday.Multi_User_Management_App.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse mapToDTO(Comment comment) {
        if (comment == null) {
            return null;
        }

        return CommentResponse.builder()
                .id(comment.getId())
                .text(comment.getText())
                .taskId(comment.getTask().getId())
                .userEmail(comment.getUser().getEmail())
                .createdDate(comment.getCreateDate())
                .build();
    }

    public Comment mapToEntity(CommentRequest commentRequest, Task task, User user) {
        if (commentRequest == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        comment.setTask(task);
        comment.setUser(user);

        return comment;
    }
}
