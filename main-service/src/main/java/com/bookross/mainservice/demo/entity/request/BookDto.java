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
    private Long id;
    private Long userID;
    private String title;
    private String author;
    private BookStatusEnum status;
    private List<String> genres;
    // todo: how to send image?
}
