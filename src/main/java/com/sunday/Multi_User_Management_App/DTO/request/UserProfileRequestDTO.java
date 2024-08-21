package com.sunday.Multi_User_Management_App.DTO.request;

import com.sunday.Multi_User_Management_App.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileRequestDTO {
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private ROLE role;
}