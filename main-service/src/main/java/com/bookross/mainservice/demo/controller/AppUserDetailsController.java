package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.annotation.MultipartConfig;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/v1/user_details")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class AppUserDetailsController {
    private final ImageService imageService;
    private final AppUserDetailsService appUserDetailsService;

    @PutMapping(path = "/updateImage/{userID}")
    public ResponseEntity<Void> updateUserImage(@PathVariable("userID") Long userID, @RequestPart("image") MultipartFile file) {
        imageService.saveUserImage(userID, file);
        return ResponseEntity.ok().build();
    }

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
}
