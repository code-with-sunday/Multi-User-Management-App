package com.sunday.Multi_User_Management_App.authService;

import com.sunday.Multi_User_Management_App.DTO.request.LoginRequest;
import com.sunday.Multi_User_Management_App.DTO.request.UserSignUpRequest;
import com.sunday.Multi_User_Management_App.DTO.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthUserDetails {

    AuthResponse signIn(@RequestBody LoginRequest loginRequest);
    AuthResponse userSignup(@RequestBody UserSignUpRequest userSignUpRequest) throws Exception;
    AuthResponse AdminSignup(@RequestBody UserSignUpRequest userSignUpRequest) throws Exception;
}

