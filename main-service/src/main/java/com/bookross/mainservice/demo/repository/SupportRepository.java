package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SupportRepository extends BaseRepository<Support, Long> {
}
