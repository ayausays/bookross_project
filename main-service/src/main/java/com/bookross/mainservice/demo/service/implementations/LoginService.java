package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.request.LoginRequest;
import com.bookross.mainservice.demo.exception.EntityNotFoundException;
import com.bookross.mainservice.demo.exception.UserException;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long login(LoginRequest loginRequest) {
        AppUser appUser = appUserService.findByEmail(loginRequest.getEmail());
        if (appUser == null) {
            throw new EntityNotFoundException(AppUser.class, "User by the given email does not exist", loginRequest.getEmail());
        }
        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())){
            return appUser.getId();
        }
        else {
            throw new UserException("User not found", true);
        }
    }
}
