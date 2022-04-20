package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok(appUserService.getAllUsers());
    }

    @PutMapping(path = "/update/{userID}")
    public ResponseEntity<Void> updateUser(@PathVariable("userID") Long userID,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String email){
        appUserService.updateAppUser(userID, firstName, lastName, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userID") Long userID){
        appUserService.deleteUser(userID);
        return ResponseEntity.ok().build();
    }

}
