package com.sunday.Multi_User_Management_App.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "Task")
@JsonPropertyOrder({"id", "tagMark", "tasks"})
public class Comment extends AuditBaseEntity{
    @NotBlank(message = "Comment text is mandatory")
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
