package com.sunday.Multi_User_Management_App.controller;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.UserProfileRequest;
import com.sunday.Multi_User_Management_App.DTO.request.UserRequest;
import com.sunday.Multi_User_Management_App.DTO.response.UserResponse;
import com.sunday.Multi_User_Management_App.enums.InternalCodeEnum;
import com.sunday.Multi_User_Management_App.exception.UnAuthorizedException;
import com.sunday.Multi_User_Management_App.exception.UserAlreadyExist;
import com.sunday.Multi_User_Management_App.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Create Profile", description = "Create a new user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-profile")
    public ResponseEntity<ApiResponseBody<UserResponse>> createProfile(@RequestBody UserProfileRequest userProfileRequest) {
        UserResponse userResponse = userService.createProfile(userProfileRequest);
        return ResponseEntity.ok(new ApiResponseBody<>(true, "Profile created successfully",
                InternalCodeEnum.CARE_PULSE_001, userResponse));
    }

    @Operation(summary = "Create User", description = "Create a new user (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-user")
    public ResponseEntity<ApiResponseBody<UserResponse>> createAdmin(@RequestBody @Valid UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.createAdmin(userRequest);
            return ResponseEntity.ok(new ApiResponseBody<>(true, "User created successfully",
                    InternalCodeEnum.CARE_PULSE_001, userResponse));
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_002, null));
        } catch (UserAlreadyExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseBody<>(false, e.getMessage(),
                    InternalCodeEnum.CARE_PULSE_003, null));
        }
    }
}
