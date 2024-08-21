package com.sunday.Multi_User_Management_App.service.Impl;

import com.sunday.Multi_User_Management_App.DTO.request.CommentRequest;
import com.sunday.Multi_User_Management_App.DTO.response.CommentResponse;
import com.sunday.Multi_User_Management_App.exception.CommentNotFoundException;
import com.sunday.Multi_User_Management_App.exception.TaskNotFoundException;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserNotFoundException;
import com.sunday.Multi_User_Management_App.mapper.CommentMapper;
import com.sunday.Multi_User_Management_App.model.Comment;
import com.sunday.Multi_User_Management_App.model.Task;
import com.sunday.Multi_User_Management_App.model.User;
import com.sunday.Multi_User_Management_App.repository.CommentRepository;
import com.sunday.Multi_User_Management_App.repository.TaskRepository;
import com.sunday.Multi_User_Management_App.repository.UserRepository;
import com.sunday.Multi_User_Management_App.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponse addComment(Long taskId, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + userEmail);
        }

        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }
        Task task = optionalTask.get();

        Comment comment = commentMapper.mapToEntity(commentRequest, task, user);
        Comment savedComment = commentRepository.save(comment);

        return commentMapper.mapToDTO(savedComment);
    }

    @Override
    public CommentResponse editComment(Long commentId, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        if (!comment.getUser().getEmail().equals(userEmail)) {
            throw new UnAuthorizedException("You are not authorized to edit this comment.");
        }

        comment.setText(commentRequest.getText());
        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        if (!comment.getUser().getEmail().equals(userEmail)) {
            throw new UnAuthorizedException("You are not authorized to delete this comment.");
        }

        commentRepository.delete(comment);
    }

    @Override
    public void deleteCommentAsAdmin(Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + userEmail);
        }

        throw new UnAuthorizedException("You do not have permission to delete comments");

    }
}
