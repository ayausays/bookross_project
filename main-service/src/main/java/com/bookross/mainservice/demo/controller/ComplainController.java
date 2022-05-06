package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.request.ComplainDto;
import com.bookross.mainservice.demo.service.interfaces.ComplainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/complain")
@RequiredArgsConstructor
public class ComplainController {
    private final ComplainService complainService;

    @PostMapping(path = "/addComplain")
    public ResponseEntity<Void> addComplain(@RequestBody ComplainDto complainDto) {
        complainService.saveComplain(complainDto);
        return ResponseEntity.ok().build();
    }
}
