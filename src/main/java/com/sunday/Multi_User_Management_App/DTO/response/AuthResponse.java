package com.sunday.Multi_User_Management_App.DTO.response;

import com.sunday.Multi_User_Management_App.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String Title;
    private String message;
    private ROLE role;
}