package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.request.LoginRequest;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(LoginRequest loginRequest){
        AppUser appUser = appUserService.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new IllegalArgumentException(String.format("User with email %s doesn't exist.", loginRequest.getEmail())));
        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())){
            return "successfully logged in";
        }
        return "invalid password. try again.";
    }
}
