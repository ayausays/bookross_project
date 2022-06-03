package com.bookross.mainservice.demo.entity.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookSearchDto {
    private String title;
    private String author;
    private Integer yearFrom;
    private Integer yearTo;
    private int pageFrom;
    private int pageTo;
    private List<String> genres;

}
