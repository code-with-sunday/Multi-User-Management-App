package com.sunday.Multi_User_Management_App.mapper;

import com.sunday.Multi_User_Management_App.DTO.request.UserProfileRequest;
import com.sunday.Multi_User_Management_App.DTO.request.UserRequest;
import com.sunday.Multi_User_Management_App.DTO.response.UserResponse;
import com.sunday.Multi_User_Management_App.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User mapToEntity(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setMiddleName(userRequest.getMiddleName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}