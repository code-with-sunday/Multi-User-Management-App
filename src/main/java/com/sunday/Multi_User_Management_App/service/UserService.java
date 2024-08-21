package com.sunday.Multi_User_Management_App.service;

import com.sunday.Multi_User_Management_App.DTO.request.UserProfileRequest;
import com.sunday.Multi_User_Management_App.DTO.request.UserRequest;
import com.sunday.Multi_User_Management_App.DTO.response.UserResponse;
import com.sunday.Multi_User_Management_App.model.User;

public interface UserService {
    UserResponse createProfile (UserProfileRequest userProfileRequest);

    UserResponse createAdmin (UserRequest userRequest);

}
