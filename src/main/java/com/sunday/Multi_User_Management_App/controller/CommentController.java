package com.sunday.Multi_User_Management_App.controller;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.CommentRequest;
import com.sunday.Multi_User_Management_App.DTO.response.CommentResponse;
import com.sunday.Multi_User_Management_App.enums.InternalCodeEnum;
import com.sunday.Multi_User_Management_App.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Add Comment to Task", description = "Allows users to add comments to a specific task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Task or User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<ApiResponseBody<CommentResponse>> addComment(
            @RequestParam Long taskId,
            @RequestBody @Valid CommentRequest commentRequest) {

        CommentResponse commentResponse = commentService.addComment(taskId, commentRequest);

        return ResponseEntity.ok(new ApiResponseBody<>(
                true, "Comment added successfully",
                InternalCodeEnum.CARE_PULSE_001, commentResponse));
    }

    @Operation(summary = "Edit Comment", description = "Allows users to edit their own comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment edited successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Comment not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized action"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<ApiResponseBody<CommentResponse>> editComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequest commentRequest) {

        CommentResponse commentResponse = commentService.editComment(commentId, commentRequest);

        return ResponseEntity.ok(new ApiResponseBody<>(
                true, "Comment edited successfully",
                InternalCodeEnum.CARE_PULSE_001, commentResponse));
    }

    @Operation(summary = "Delete Comment", description = "Allows users to delete their own comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized action"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponseBody<Void>> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok(new ApiResponseBody<>(
                true, "Comment deleted successfully",
                InternalCodeEnum.CARE_PULSE_001, null));
    }

}
