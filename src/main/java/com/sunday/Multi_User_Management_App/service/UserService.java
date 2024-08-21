package com.sunday.Multi_User_Management_App.service;


import com.sunday.Multi_User_Management_App.model.User;

public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;

}
