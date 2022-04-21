package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Complain;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ComplainRepository extends BaseRepository<Complain, Long> {
}