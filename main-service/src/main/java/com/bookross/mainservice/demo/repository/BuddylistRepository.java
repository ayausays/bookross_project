package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.AppUserRole;
import com.bookross.mainservice.demo.entity.BuddyList;
import com.bookross.mainservice.demo.entity.response.BuddyListGetResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface BuddylistRepository extends  JpaRepository<BuddyList,Long> {
    @Query(
            value = "select new com.bookross.mainservice.demo.entity.response.BuddyListGetResources(b.id,b.senderId,b.isRead,b.link,b.createdAt,b.updatedAt,u.firstName,u.lastName)" +
                    " from BuddyList b left join AppUser u on b.senderId=u.id  WHERE b.receiverId=:userId",nativeQuery = false)
    Page<BuddyListGetResources> findByReceiverId(@Param("userId") Long userId, Pageable pageable);


    Long countByReceiverIdAndIsRead(Long userId, boolean b);

    Optional<BuddyList> findBySenderIdAndReceiverId(Long receiver, Long userid);

    void deleteBySenderIdAndReceiverId(Long userid, Long receiver);


}
