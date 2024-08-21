package com.sunday.Multi_User_Management_App.controller;

import com.sunday.Multi_User_Management_App.DTO.ApiResponseBody;
import com.sunday.Multi_User_Management_App.DTO.request.LoginRequestDTO;
import com.sunday.Multi_User_Management_App.DTO.request.UserSignUpRequest;
import com.sunday.Multi_User_Management_App.DTO.response.AuthResponse;
import com.sunday.Multi_User_Management_App.authService.AuthUserDetails;
import com.sunday.Multi_User_Management_App.enums.InternalCodeEnum;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth API")
@OpenAPIDefinition(info = @Info(title = "Auth API", version = "1.0", description = "Auth API"))
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserDetails authUserDetails;

    @Operation(summary = "Create user", description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/signup")

    public ResponseEntity<ApiResponseBody<AuthResponse>> userSignup(@RequestBody @Valid UserSignUpRequest userSignUpRequest) throws Exception {
        return ResponseEntity.ok(new ApiResponseBody<>(true, "User created successfully",
                InternalCodeEnum.CARE_PULSE_001, authUserDetails.userSignup(userSignUpRequest)));

    }


    @Operation(summary = "Login", description = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponseBody<AuthResponse>> signIn(@RequestBody LoginRequestDTO loginRequest){
        return ResponseEntity.ok(new ApiResponseBody<>(true, "User logged in successfully",
                InternalCodeEnum.CARE_PULSE_001, authUserDetails.signIn(loginRequest)));
    }


}