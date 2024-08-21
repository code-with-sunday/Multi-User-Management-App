package com.sunday.Multi_User_Management_App.mapper;

import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;

import com.sunday.Multi_User_Management_App.model.Task;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    public TaskResponse mapToDTO(Task task) {
        if (task == null) {
            return null;
        }

        return TaskResponse.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .assignedUser(task.getAssignedUser())
                .createdBy(task.getCreatedBy())
                .build();
    }

    public Task mapToEntity(TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }

        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .status(taskRequest.getStatus())
                .tags(taskRequest.getTags())
                .build();
    }
}
