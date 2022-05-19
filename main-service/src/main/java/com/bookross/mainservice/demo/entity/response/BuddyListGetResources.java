package com.bookross.mainservice.demo.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BuddyListGetResources {

    private String profilePicture;
    private String name;
    private Long id;
    private Long senderId;
    private Boolean isRead;
    private Long link;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private byte status;

    public BuddyListGetResources(Long id, Long senderId, Boolean isRead, Long link, LocalDateTime createdAt, LocalDateTime updatedAt, String firstame,String lastname, String photo) {
        this.id = id;
        this.senderId = senderId;
        this.isRead = isRead;
        this.link = link;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = firstame+" "+lastname;
        this.profilePicture = photo;
    }

}
