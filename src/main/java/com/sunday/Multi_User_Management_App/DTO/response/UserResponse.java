package com.sunday.Multi_User_Management_App.DTO.response;

import com.sunday.Multi_User_Management_App.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private ROLE role;
}
