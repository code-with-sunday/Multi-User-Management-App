package com.sunday.Multi_User_Management_App.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
public enum InternalCodeEnum {
    CARE_PULSE_001(1, "Request performed successfully", OK),
    CARE_PULSE_002(2, "Access denied", HttpStatus.FORBIDDEN),
    CARE_PULSE_003(3, "User already exists", HttpStatus.BAD_REQUEST),
    CARE_PULSE_004(4, "User not found", HttpStatus.NOT_FOUND),
    CARE_PULSE_005(5, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String codeDescription;
    private final String codeNumber;
    private final HttpStatus httpStatus;

    InternalCodeEnum(int codeNumber, String codeDescription, HttpStatus status) {
        this.codeNumber = String.format("%03d", codeNumber);
        this.codeDescription = codeDescription;
        this.httpStatus = status;
    }
}
