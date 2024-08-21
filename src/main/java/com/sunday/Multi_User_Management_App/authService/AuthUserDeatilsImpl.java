package com.sunday.Multi_User_Management_App.authService;


import com.sunday.Multi_User_Management_App.DTO.request.LoginRequestDTO;
import com.sunday.Multi_User_Management_App.DTO.request.UserSignUpRequest;
import com.sunday.Multi_User_Management_App.DTO.response.AuthResponse;
import com.sunday.Multi_User_Management_App.enums.ROLE;
import com.sunday.Multi_User_Management_App.exception.UserAlreadyExist;
import com.sunday.Multi_User_Management_App.mapper.UserMapper;
import com.sunday.Multi_User_Management_App.model.User;
import com.sunday.Multi_User_Management_App.repository.UserRepository;
import com.sunday.Multi_User_Management_App.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthUserDeatilsImpl implements AuthUserDetails{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsImpl customUserDetails;
    private final UserMapper userMapper;


    @Override

    public AuthResponse userSignup(UserSignUpRequest userSignUpRequest) throws Exception {
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());


        User isEmailExist  = userRepository.findByEmail(user.getEmail());

        if(isEmailExist != null){
            throw new UserAlreadyExist("Email already exist with another account");
        }

        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        User savedUser = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("Welcome " + user.getEmail());
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());

        return authResponse;
    }


    @Override
    public AuthResponse signIn(LoginRequestDTO loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? String.valueOf(ROLE.USER) : authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login success");
        authResponse.setTitle(jwt);
        authResponse.setRole(ROLE.valueOf(role));

        return authResponse;
    }



    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}
