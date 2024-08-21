package com.sunday.Multi_User_Management_App.DTO.request;

import com.sunday.Multi_User_Management_App.enums.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "firstname is mandatory")
    @NotNull(message = "firstname is mandatory")
    @NotEmpty(message = "firstname is mandatory")
    private String firstName;

    private String middleName;

    @NotBlank(message = "lastname is mandatory")
    @NotNull(message = "lastname is mandatory")
    @NotEmpty(message = "lastname is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;

}
