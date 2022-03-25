package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    public String login(LoginRequest loginRequest){
        return "successfully logged in";
    }
}
