package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.request.SupportDto;
import com.bookross.mainservice.demo.entity.Support;
import com.bookross.mainservice.demo.service.interfaces.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;

    @PostMapping(path = "/addSupport")
    public ResponseEntity<Void> addSupport(@RequestBody SupportDto supportDto){
        supportService.saveSupport(supportDto);
        return ResponseEntity.ok().build();
    }

}
