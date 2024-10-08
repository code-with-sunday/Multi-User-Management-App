package com.sunday.Multi_User_Management_App.controller;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.TaskRequest;
import com.sunday.Multi_User_Management_App.DTO.response.TaskResponse;
import com.sunday.Multi_User_Management_App.enums.InternalCodeEnum;
import com.sunday.Multi_User_Management_App.enums.TagMark;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import com.sunday.Multi_User_Management_App.exception.TaskNotFoundException;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserNotFoundException;
import com.sunday.Multi_User_Management_App.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create Task", description = "Create a new task (Authenticated Users only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseBody<TaskResponse>> createTask(
            @RequestBody @Valid TaskRequest taskRequest,
            @RequestParam TagMark tagMarks) {

        TaskResponse taskResponse = taskService.createTask(taskRequest);

        return ResponseEntity.ok(new ApiResponseBody<>(true, "Task created successfully",
                InternalCodeEnum.CARE_PULSE_001, taskResponse));
    }

    @Operation(summary = "Assign Task", description = "Assign a task to yourself or another user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task assigned successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/assign")
    public ResponseEntity<ApiResponseBody<TaskResponse>> assignTask(@RequestBody @Valid TaskRequest taskRequest) {
        try {
            TaskResponse taskResponse = taskService.assignTask(taskRequest);
            return ResponseEntity.ok(new ApiResponseBody<>(true, "Task assigned successfully",
                    InternalCodeEnum.CARE_PULSE_001, taskResponse));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_004, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_005, null));
        }
    }

    @Operation(summary = "Update Task Status", description = "Update the status of your own task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{taskId}/status")
    public ResponseEntity<ApiResponseBody<TaskResponse>> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatus status) {
        TaskResponse taskResponse = taskService.updateTaskStatus(taskId, status);

        return ResponseEntity.ok(new ApiResponseBody<>(true, "Task status updated successfully",
                InternalCodeEnum.CARE_PULSE_001, taskResponse));
    }

    @Operation(summary = "Update Task Status", description = "Update the status of a task. Admins can update any task, users can update their own tasks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/admin/{taskId}/status")
    public ResponseEntity<ApiResponseBody<TaskResponse>> adminUpdateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatus status) {
        try {
            TaskResponse taskResponse = taskService.updateTaskStatus(taskId, status);
            return ResponseEntity.ok(new ApiResponseBody<>(true, "Task status updated successfully",
                    InternalCodeEnum.CARE_PULSE_001, taskResponse));
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_002, null));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_003, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseBody<>(false, "Internal server error",
                    InternalCodeEnum.CARE_PULSE_004, null));
        }
    }

    @Operation(summary = "Filter Tasks by Tags", description = "Retrieve tasks filtered by tags with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/tasks/filter")
    public ResponseEntity<ApiResponseBody<ApiResponseBody.Wrapper<List<TaskResponse>>>> filterTasksByTags(
            @RequestParam List<TagMark> tagMarks,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        ApiResponseBody.Wrapper<List<TaskResponse>> paginatedTasks = taskService.filterTasksByTags(tagMarks, pageable);

        ApiResponseBody<ApiResponseBody.Wrapper<List<TaskResponse>>> responseBody = new ApiResponseBody<>(
                true, "Tasks retrieved successfully",
                InternalCodeEnum.CARE_PULSE_001, paginatedTasks
        );

        return ResponseEntity.ok(responseBody);
    }

}