package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ComplainDto {
    Long complainID;
    String loginOfUser;
    String complainType;
    String complainComment;
}