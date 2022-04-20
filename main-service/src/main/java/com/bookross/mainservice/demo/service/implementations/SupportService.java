package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Support;
import com.bookross.mainservice.demo.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportService {
    @Autowired
    private SupportRepository repository;

    public void save(Support support) {
        repository.save(support);
    }
}
