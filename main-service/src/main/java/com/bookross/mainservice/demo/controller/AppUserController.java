package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    // todo: make dtos for all entities to return them for get requests

    // works postman
    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    // todo: not urgent, but return pageable, not list.
    // works postman
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok(appUserService.getAllUsers());
    }

    // works postman
    @PutMapping(path = "/update/{userID}")
    public ResponseEntity<Void> updateUser(@PathVariable("userID") Long userID,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String email){
        appUserService.updateAppUser(userID, firstName, lastName, email);
        return ResponseEntity.ok().build();
    }

    // works postman
    @DeleteMapping(path = "/delete/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userID") Long userID){
        appUserService.deleteUser(userID);
        return ResponseEntity.ok().build();
    }

}
