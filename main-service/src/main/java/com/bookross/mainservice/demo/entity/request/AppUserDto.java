package com.bookross.mainservice.demo.entity.request;

import com.bookross.mainservice.demo.entity.AppUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class AppUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private AppUserRole appUserRole;
}
