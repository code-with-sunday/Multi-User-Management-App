package com.sunday.Multi_User_Management_App.service;

import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse assignTask(TaskRequest taskRequest);
    TaskResponse updateTaskStatus(Long taskId, TaskStatus status);
    TaskResponse AdminupdateTaskStatus(Long taskId, TaskStatus status);
}
