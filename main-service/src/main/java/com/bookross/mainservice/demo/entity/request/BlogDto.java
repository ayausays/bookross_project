package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BlogDto {
    private Long userID;
    private String topic;
    private String blogText;
    private LocalDateTime dateOfPublication;
}
