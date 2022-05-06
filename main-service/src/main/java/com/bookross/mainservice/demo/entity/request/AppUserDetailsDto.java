package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AppUserDetailsDto {
    private Long userID;
    private String city;
    private LocalDate dob;
    private String phoneNumber;
    private String aboutUser;
}
