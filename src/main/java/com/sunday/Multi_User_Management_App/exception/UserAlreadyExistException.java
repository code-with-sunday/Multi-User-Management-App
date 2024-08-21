package com.sunday.Multi_User_Management_App.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg){
        super(msg);
    }

}
