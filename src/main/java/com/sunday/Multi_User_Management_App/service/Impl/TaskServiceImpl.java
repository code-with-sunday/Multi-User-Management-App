package com.sunday.Multi_User_Management_App.service.Impl;

import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;
import com.sunday.Multi_User_Management_App.enums.ROLE;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import com.sunday.Multi_User_Management_App.exception.TaskNotFound;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserNotFound;
import com.sunday.Multi_User_Management_App.mapper.TaskMapper;
import com.sunday.Multi_User_Management_App.model.Task;
import com.sunday.Multi_User_Management_App.model.User;
import com.sunday.Multi_User_Management_App.repository.TaskRepository;
import com.sunday.Multi_User_Management_App.repository.UserRepository;
import com.sunday.Multi_User_Management_App.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new UserNotFound("User with email " + email + " already exists");
        }
        Task task = taskMapper.mapToEntity(taskRequest, user);
        Task savedTask = taskRepository.save(task);

        return taskMapper.mapToDTO(savedTask);
    }

    @Override
    public TaskResponse assignTask(TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String creatorEmail = (String) authentication.getPrincipal();
        User creator = userRepository.findByEmail(creatorEmail);

        if (creator == null) {
            throw new UserNotFound("Creator not found with email: " + creatorEmail);
        }

        User assignedUser = userRepository.findByEmail(taskRequest.getAssignedUserEmail());

        if (assignedUser == null) {
            throw new UserNotFound("Assigned user not found with email: " + taskRequest.getAssignedUserEmail());
        }

        Task task = taskMapper.mapToEntity(taskRequest, assignedUser);
        task.setCreatedBy(creator);
        Task savedTask = taskRepository.save(task);

        return taskMapper.mapToDTO(savedTask);
    }

    @Override
    public TaskResponse updateTaskStatus(Long taskId, TaskStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        User currentUser = userRepository.findByEmail(userEmail);
        if (currentUser == null) {
            throw new UserNotFound("Creator not found with email: " + currentUser.getEmail());
        }
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotFound("Task id not found: " + task.getId());
        }

        if (!task.getAssignedUser().equals(currentUser)) {
            return null;
        }

        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);

        return taskMapper.mapToDTO(updatedTask);
    }

    @Override
    public TaskResponse AdminupdateTaskStatus(Long taskId, TaskStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        User currentUser = userRepository.findByEmail(email);
        if (currentUser == null) {
            throw new UserNotFound("User not found with email: " + email);
        }

        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotFound("Task not found with id: " + taskId);
        }

        if (!currentUser.getRole().equals(ROLE.ADMIN) && !task.getAssignedUser().equals(currentUser)) {
            throw new UnAuthorizedException("You are not authorized to update this task.");
        }

        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);

        return taskMapper.mapToDTO(updatedTask);
    }

}