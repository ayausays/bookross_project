package com.bookross.mainservice.demo.controller;


import com.bookross.mainservice.demo.entity.request.AppUserDto;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.entity.request.BookSearchDto;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final BookService bookService;
    private final AppUserService appUserService;

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestParam("value") String value){
        return ResponseEntity.ok(bookService.searchBooks(value));
    }

    @GetMapping(path = "/extended")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestBody BookSearchDto bookSearchDto){
        return ResponseEntity.ok(bookService.searchBooksExtended(bookSearchDto));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<AppUserDto>> searchUsers(@RequestParam("value") String value){
        return ResponseEntity.ok(appUserService.searchUsers(value));
    }


}
