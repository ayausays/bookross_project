package com.bookross.mainservice.demo.controller;

import com.bookross.mainservice.demo.entity.Support;
import com.bookross.mainservice.demo.service.implementations.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/support")
@RestController
public class SupportController {

    @Autowired
    private SupportService supportService;

    @PostMapping
    public void add(@RequestBody Support support) {
        supportService.save(support);
    }
}
