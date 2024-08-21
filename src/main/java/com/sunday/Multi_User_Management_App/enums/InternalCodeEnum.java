package com.sunday.Multi_User_Management_App.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
public enum InternalCodeEnum {
    CARE_PULSE_001(1, "Request performed successfully", OK);

    private final String codeDescription;
    private final String codeNumber;
    private final HttpStatus httpStatus;

    InternalCodeEnum(int codeNumber, String codeDescription, HttpStatus status) {
        this.codeNumber = String.format("%03d", codeNumber);
        this.codeDescription = codeDescription;
        this.httpStatus = status;
    }
}
