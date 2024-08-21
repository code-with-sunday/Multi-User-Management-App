package com.sunday.Multi_User_Management_App.mapper;

import com.sunday.Multi_User_Management_App.DTO.request.UserProfileRequestDTO;
import com.sunday.Multi_User_Management_App.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileRequestDTO mapToDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserProfileRequestDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User mapToEntity(UserProfileRequestDTO userProfileRequestDTO) {
        if (userProfileRequestDTO == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userProfileRequestDTO.getUsername());
        user.setFirstName(userProfileRequestDTO.getFirstName());
        user.setMiddleName(userProfileRequestDTO.getMiddleName());
        user.setLastName(userProfileRequestDTO.getLastName());
        user.setEmail(userProfileRequestDTO.getEmail());
        user.setRole(userProfileRequestDTO.getRole());
        user.setPassword(userProfileRequestDTO.getPassword());
        return user;
    }
}