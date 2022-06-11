package com.bookross.mainservice.demo.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMessageResponse {
    private Long messageId;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;

    public GetMessageResponse(Long id ,String message, LocalDateTime createdAt,Long messageId){
        this.messageId=messageId;
        this.userId=id;
        this.message=message;
        this.createdAt=createdAt;
    }
}
