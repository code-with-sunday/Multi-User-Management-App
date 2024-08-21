package com.sunday.Multi_User_Management_App.service.Impl;

import com.sunday.Multi_User_Management_App.exception.UserNotFound;
import com.sunday.Multi_User_Management_App.mapper.UserMapper;
import com.sunday.Multi_User_Management_App.model.User;
import com.sunday.Multi_User_Management_App.repository.UserRepository;
import com.sunday.Multi_User_Management_App.security.JwtProvider;
import com.sunday.Multi_User_Management_App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFound("User not found");
        }
        return user;
    }

}
