package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SupportDto {
    Long supportID;
    String supportEmail;
    String supportType;
    String supportComment;
}
