package com.sunday.Multi_User_Management_App.controller;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.UserRequest;
import com.sunday.Multi_User_Management_App.DTO.response.UserResponse;
import com.sunday.Multi_User_Management_App.enums.InternalCodeEnum;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserAlreadyExistException;
import com.sunday.Multi_User_Management_App.service.CommentService;
import com.sunday.Multi_User_Management_App.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class adminController {

    private final UserService userService;
    private final CommentService commentService;

    @Operation(summary = "Create User", description = "Create a new user (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseBody<UserResponse>> createAdmin(@RequestBody @Valid UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.createAdmin(userRequest);
            return ResponseEntity.ok(new ApiResponseBody<>(true, "User created successfully",
                    InternalCodeEnum.CARE_PULSE_001, userResponse));
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_002, null));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_003, null));
        }
    }

    @DeleteMapping("/{commentId}/admin-delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Comment as Admin", description = "Allows admins to delete any comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    public ResponseEntity<ApiResponseBody<Void>> deleteCommentAsAdmin(@PathVariable Long commentId) {
        commentService.deleteCommentAsAdmin(commentId);
        return ResponseEntity.ok(new ApiResponseBody<>(true, "Comment deleted successfully", InternalCodeEnum.CARE_PULSE_001, null));
    }
}
