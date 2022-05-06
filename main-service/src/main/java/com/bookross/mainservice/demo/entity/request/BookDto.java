package com.bookross.mainservice.demo.entity.request;

import com.bookross.mainservice.demo.entity.BookStatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookDto {
    Long userID;
    String title;
    String author;
    BookStatusEnum status;
    List<String> genres;
}
