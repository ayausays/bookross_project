package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class LoginRequest {
    private final String email;
    private final String password;
}
