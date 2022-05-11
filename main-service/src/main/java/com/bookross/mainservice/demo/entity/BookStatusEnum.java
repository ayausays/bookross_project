package com.bookross.mainservice.demo.entity;

import lombok.Getter;

@Getter
public enum BookStatusEnum {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");

    String value;

    BookStatusEnum(String value) {
        this.value = value;
    }
}
