package com.bookross.mainservice.demo.entity.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookDto {
    Long userID;
    String title;
    String author;
    String status;
}
