package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.AppUserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserDetailsRepository extends BaseRepository<AppUserDetails, Long>{
    @Query("UPDATE AppUserDetails a " +
            "SET a.imagePath = ?2 WHERE a.id= ?1")
    void updateAppUserImagePath(Long id, String path);
}
