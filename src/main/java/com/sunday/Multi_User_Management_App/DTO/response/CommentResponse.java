package com.sunday.Multi_User_Management_App.DTO.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String text;
    private Long taskId;
    private String userEmail;
    private LocalDateTime createdDate;
}
