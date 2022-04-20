package com.bookross.mainservice.demo.controller;


import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user_books")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class BookController {

    private final ImageService imageService;
    private final BookService bookService;

    @GetMapping(path = "/getUserBooks/{userID}")
    public ResponseEntity<List<Book>> getBooksById(@PathVariable("userID") Long id){
        return ResponseEntity.ok(bookService.findBooksByUserID(id));
    }

    @GetMapping(path = "/getBook/{bookID}")
    public ResponseEntity<Book> getBook(@PathVariable("bookID") Long id){
        return ResponseEntity.ok(bookService.findBook(id));
    }

    @PostMapping(path = "/addBook")
    public ResponseEntity<Void> addBook(@RequestParam BookDto bookDto){
        bookService.saveBook(bookDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateBook/{bookID}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookID") Long id,
                                           @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String author,
                                           @RequestParam(required = false) String status
                                           ){
        bookService.updateBook(id, title, author, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateBookImage/{bookID}")
    public ResponseEntity<Void> updateBookImage(@PathVariable("bookID") Long id, @RequestPart("image") MultipartFile file){
        imageService.saveBookImage(id, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteBook/{bookID}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookID") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }



}
