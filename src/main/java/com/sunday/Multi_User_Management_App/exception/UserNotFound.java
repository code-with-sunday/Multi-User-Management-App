package com.sunday.Multi_User_Management_App.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String msg){
        super(msg);
    }
}
