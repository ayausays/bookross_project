package com.bookross.mainservice.demo.entity;

import lombok.Getter;

@Getter
public enum BookStatusEnum {
    AVAILABLE("AVAILABLE", "Доступен"),
    UNAVAILABLE("UNAVAILABLE", "Недоступен");

    String code;
    String description;

    BookStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
