package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.request.BookDto;

import java.util.List;

public interface BookService extends BaseService<Book, Long> {
    void saveBook(BookDto bookDto);
    List<BookDto> findBooksByUserID(Long userID);
    BookDto findBook(Long id);
    void deleteBook(Long id);
    void updateBook(Long id, String title, String author, String status, String[] genres);
    void addBookToUserFavs(Long userID, Long bookID);
    void deleteBookFromUserFavs(Long userID, Long bookID);
    List<BookDto> getUserFavBooks(Long userID);
}
