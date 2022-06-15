package com.bookross.mainservice.demo.controller;


import com.bookross.mainservice.demo.entity.request.LoginRequest;
import com.bookross.mainservice.demo.entity.request.RegistrationRequest;
import com.bookross.mainservice.demo.service.implementations.LoginService;
import com.bookross.mainservice.demo.service.implementations.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/registration")
@RequiredArgsConstructor
public class MainController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "register", consumes = {"application/json"})
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Long> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(loginService.login(loginRequest));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
