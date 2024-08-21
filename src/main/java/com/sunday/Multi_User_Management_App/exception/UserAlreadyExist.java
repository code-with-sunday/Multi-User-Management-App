package com.sunday.Multi_User_Management_App.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String msg){
        super(msg);
    }

}
