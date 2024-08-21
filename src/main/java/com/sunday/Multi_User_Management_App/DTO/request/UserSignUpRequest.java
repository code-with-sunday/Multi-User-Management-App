package com.sunday.Multi_User_Management_App.DTO.request;

import jakarta.validation.constraints.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSignUpRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    private String password;
}
