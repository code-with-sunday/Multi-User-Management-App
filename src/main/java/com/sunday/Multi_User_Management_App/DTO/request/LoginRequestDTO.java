package com.sunday.Multi_User_Management_App.DTO.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}