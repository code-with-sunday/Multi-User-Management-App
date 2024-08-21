package com.sunday.Multi_User_Management_App.service;

import com.sunday.Multi_User_Management_App.DTO.request.MailRequest;

public interface EmailService {
    void sendEmailAlert(MailRequest mailDTO);
}
