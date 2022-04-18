package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support,Integer> {
}
