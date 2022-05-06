package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.request.AppUserDetailsDto;
import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.annotation.MultipartConfig;
import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/userDetails")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class AppUserDetailsController {
    private final ImageService imageService;
    private final AppUserDetailsService appUserDetailsService;

    // todo: make dtos for all entities to return them for get requests

    @PutMapping(path = "/updateImage/{userID}")
    // works postman
    public ResponseEntity<Void> updateUserImage(@PathVariable("userID") Long userID, @RequestPart("image") MultipartFile file) {
        imageService.saveUserImage(userID, file);
        return ResponseEntity.ok().build();
    }

    // todo: ref city
    // todo: dob update fix it
    // works postman
    @PutMapping(path = "/updateUserDetails/{userID}")
    public ResponseEntity<Void> updateUserDetails(@PathVariable("userID") Long userID,
                                                  @RequestParam(required = false) String city,
                                                  @RequestParam(required = false) LocalDate dob,
                                                  @RequestParam(required = false) String phoneNumber,
                                                  @RequestParam(required = false) String aboutUser
                                                  ){
        appUserDetailsService.updateAppUserDetails(userID, city, dob, phoneNumber, aboutUser);
        return ResponseEntity.ok().build();
    }

    // works postman
    @GetMapping(path = "/getUserDetails/{userID}")
    public ResponseEntity<AppUserDetailsDto> getUserDetails(@PathVariable("userID") Long userID){
        return ResponseEntity.ok(appUserDetailsService.getAppUserDetails(userID));
    }

}
