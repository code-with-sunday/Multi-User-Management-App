package com.sunday.Multi_User_Management_App.service;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;
import com.sunday.Multi_User_Management_App.enums.TagMark;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse assignTask(TaskRequest taskRequest);
    TaskResponse updateTaskStatus(Long taskId, TaskStatus status);
    TaskResponse AdminupdateTaskStatus(Long taskId, TaskStatus status);
    ApiResponseBody.Wrapper<List<TaskResponse>> filterTasksByTags(List<TagMark> tagMarks, Pageable pageable);
}
