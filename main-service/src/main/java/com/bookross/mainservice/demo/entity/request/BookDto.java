package com.bookross.mainservice.demo.entity.request;

import com.bookross.mainservice.demo.entity.BookStatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
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
    private String description;
    private String bookImagePath;
    private LocalDateTime dateOfAdd;
    private Integer year;
    private int pageAmount;
    private String userFIO;
    private String imgData;
}
