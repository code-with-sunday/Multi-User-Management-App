package com.sunday.Multi_User_Management_App.DTO.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailRequest {
    private String to;
    private String subject;
    private String message;
}

