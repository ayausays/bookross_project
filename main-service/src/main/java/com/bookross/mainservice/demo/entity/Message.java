package com.bookross.mainservice.demo.entity;


import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Communication Id
    @Column(name = "cid", nullable = false)
    private Long communicationId;

    private String message;



    @Column(updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
