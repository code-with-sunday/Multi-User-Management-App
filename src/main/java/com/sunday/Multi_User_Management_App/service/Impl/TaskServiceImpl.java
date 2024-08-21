package com.sunday.Multi_User_Management_App.service.Impl;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;
import com.sunday.Multi_User_Management_App.enums.ROLE;
import com.sunday.Multi_User_Management_App.enums.TagMark;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import com.sunday.Multi_User_Management_App.exception.TaskNotFound;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserNotFound;
import com.sunday.Multi_User_Management_App.mapper.TaskMapper;
import com.sunday.Multi_User_Management_App.model.Task;
import com.sunday.Multi_User_Management_App.model.User;
import com.sunday.Multi_User_Management_App.repository.TagRepository;
import com.sunday.Multi_User_Management_App.repository.TaskRepository;
import com.sunday.Multi_User_Management_App.repository.UserRepository;
import com.sunday.Multi_User_Management_App.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFound("User not found with email: " + email);
        }

        User assignedUser = userRepository.findByEmail(taskRequest.getAssignedUserEmail());
        if (assignedUser == null) {
            throw new UserNotFound("Assigned user not found with email: " + taskRequest.getAssignedUserEmail());
        }

        Task task = taskMapper.mapToEntity(taskRequest);
        task.setCreatedBy(user);
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

        Task task = taskMapper.mapToEntity(taskRequest);
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

    @Override
    public ApiResponseBody.Wrapper<List<TaskResponse>> filterTasksByTags(List<TagMark> tagMarks, Pageable pageable) {
        Page<Task> taskPage = taskRepository.findByTags_TagMarkIn(tagMarks, pageable);

        List<TaskResponse> taskResponses = taskPage.stream()
                .map(taskMapper::mapToDTO)
                .collect(Collectors.toList());

        return new ApiResponseBody.Wrapper<>(
                taskResponses,
                taskPage.getTotalPages(),
                taskPage.getTotalElements(),
                taskPage.getNumberOfElements(),
                taskPage.getNumber(),
                taskPage.isLast(),
                taskPage.isFirst(),
                taskPage.isEmpty()
        );
    }

}