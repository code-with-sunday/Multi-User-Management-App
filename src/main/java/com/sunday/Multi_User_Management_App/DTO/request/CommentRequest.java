package com.sunday.Multi_User_Management_App.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRequest {
    @NotBlank(message = "Comment text is mandatory")
    private String text;
}
