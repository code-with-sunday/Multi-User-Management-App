package com.sunday.Multi_User_Management_App.DTO.response;

import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import com.sunday.Multi_User_Management_App.model.User;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
    private User assignedUser;
    private User createdBy;

}
