package com.sunday.Multi_User_Management_App.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sunday.Multi_User_Management_App.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "Task")
@JsonPropertyOrder({"id", "title", "description", "dueDate", "status", "assignedUser"})
public class Task extends AuditBaseEntity {

    private String title;
    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    @ManyToOne
    @JoinColumn(name = "created_by_id", updatable = false)
    private User createdBy;

}