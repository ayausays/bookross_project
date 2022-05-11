package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.request.AppUserDetailsDto;
import com.bookross.mainservice.demo.service.interfaces.AppUserDetailsService;
import com.bookross.mainservice.demo.service.interfaces.CityService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/userDetails")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class AppUserDetailsController {
    private final ImageService imageService;
    private final AppUserDetailsService appUserDetailsService;
    private final CityService cityService;

    @PutMapping(path = "/updateImage/{userID}")
    public ResponseEntity<Void> updateUserImage(@PathVariable("userID") Long userID, @RequestPart("image") MultipartFile file) {
        imageService.saveUserImage(userID, file);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/saveUserDetails")
    public ResponseEntity<Void> saveUserDetails(@RequestBody AppUserDetailsDto appUserDetailsDto){
        appUserDetailsService.saveOrUpdateAppUserDetails(appUserDetailsDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getUserDetails/{userID}")
    public ResponseEntity<AppUserDetailsDto> getUserDetails(@PathVariable("userID") Long userID){
        return ResponseEntity.ok(appUserDetailsService.getAppUserDetails(userID));
    }

    @GetMapping("/getCities")
    public ResponseEntity<List<String>> getCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

}
