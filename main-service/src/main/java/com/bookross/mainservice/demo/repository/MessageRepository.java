package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Message;
import com.bookross.mainservice.demo.entity.response.GetMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query(value = "select new com.bookross.mainservice.demo.entity.response.GetMessageResponse(b.senderId,m.message,m.createdAt,m.id) from BuddyList as b" +
            " LEFT JOIN Message as m on m.communicationId=b.id" +
            " WHERE (b.senderId=:buddyId AND b.receiverId=:me) OR  (b.senderId=:me AND b.receiverId=:buddyId) ORDER BY m.createdAt DESC",nativeQuery = false)

    Page<GetMessageResponse> getAUserMessages(@Param("buddyId")long buddyId, @Param("me") Long me, Pageable pageable);

}