package com.bookross.mainservice.demo.controller;


import com.bookross.mainservice.demo.entity.BookStatusEnum;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import com.bookross.mainservice.demo.service.interfaces.GenreService;
import com.bookross.mainservice.demo.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/userBooks")
@RequiredArgsConstructor
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1920 * 1080 * 50, maxRequestSize = 1920 * 1080 * 100)
public class BookController {

    private final ImageService imageService;
    private final BookService bookService;
    private final GenreService genreService;

    @GetMapping(path = "/getGenres")
    public ResponseEntity<List<String>> getGenres(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping(path = "/getUserBooks/{userID}")
    public ResponseEntity<List<BookDto>> getBooksByUserId(@PathVariable("userID") Long id){
        return ResponseEntity.ok(bookService.findBooksByUserID(id, BookStatusEnum.AVAILABLE));
    }

    @GetMapping(path = "/getArchived/{userID}")
    public ResponseEntity<List<BookDto>> getArchivedBooksByUserId(@PathVariable("userID") Long id){
        return ResponseEntity.ok(bookService.findBooksByUserID(id, BookStatusEnum.UNAVAILABLE));
    }


    @GetMapping(path = "/getBook/{bookID}")
    public ResponseEntity<BookDto> getBook(@PathVariable("bookID") Long id){
        return ResponseEntity.ok(bookService.findBook(id));
    }


    @PostMapping(path = "/addBook")
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto){
        bookService.saveBook(bookDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateBook")
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto){
        bookService.updateBook(bookDto);
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

    @GetMapping(path = "/getAllFavs/{userID}")
    public ResponseEntity<List<BookDto>> getAllFavBooks(@PathVariable("userID") Long userID){
        return ResponseEntity.ok(bookService.getUserFavBooks(userID));
    }

    @PostMapping(path = "/addToFavs")
    public ResponseEntity<Void> addBookToFavs(@RequestParam("userID") Long userID,
                                              @RequestParam("bookID") Long bookID){
        bookService.addBookToUserFavs(userID, bookID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteFromFavs")
    public ResponseEntity<Void> deleteBookFromFavs(@RequestParam("userID") Long userID,
                                                   @RequestParam("bookID") Long bookID){
        bookService.deleteBookFromUserFavs(userID, bookID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getBookStatuses")
    public ResponseEntity<Map<String, String>> getBookStatuses(){
        List<BookStatusEnum> statuses =  Arrays.asList(BookStatusEnum.values());
        return ResponseEntity.ok(statuses.stream()
                .collect(Collectors.toMap(BookStatusEnum::getCode, BookStatusEnum::getDescription)));
    }

}
