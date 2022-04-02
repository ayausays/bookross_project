package com.bookross.mainservice.demo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String senderName;
    private String receiverName;
    private String messageContent;
    private String date;
    private MessageStatus status;
}
