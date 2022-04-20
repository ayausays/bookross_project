package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class RegistrationRequest {
    @Pattern(message = "invalid first name", regexp = "^[a-zA-Z]{3,}$")
    private final String firstName;
    @Pattern(message = "invalid last name", regexp = "^[a-zA-Z]{3,}$")
    private final String lastName;
    @Email(message = "invalid email", regexp = ".+@.+\\..+")
    private final String email;
    @Pattern(message = "invalid password", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{6,20}$")
    private final String password;
}
