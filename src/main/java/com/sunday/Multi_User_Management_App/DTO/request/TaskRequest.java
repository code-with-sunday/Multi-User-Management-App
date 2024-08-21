package com.sunday.Multi_User_Management_App.DTO.request;

import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "firstname is mandatory")
    @NotNull(message = "firstname is mandatory")
    @NotEmpty(message = "firstname is mandatory")
    private String title;

    @NotBlank(message = "firstname is mandatory")
    @NotNull(message = "firstname is mandatory")
    @NotEmpty(message = "firstname is mandatory")
    private String description;

    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String assignedUserEmail;

}
