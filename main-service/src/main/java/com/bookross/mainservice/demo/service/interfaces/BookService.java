package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.request.BookDto;

import java.util.List;

public interface BookService extends BaseService<Book, Long> {
    void saveBook(BookDto bookDto);
    List<Book> findBooksByUserID(Long userID);
    Book findBook(Long id);
    void deleteBook(Long id);
    void updateBook(Long id, String title, String author, String status);
}
