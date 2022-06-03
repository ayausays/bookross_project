package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.BookStatusEnum;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.entity.request.BookSearchDto;

import java.util.List;

public interface BookService extends BaseService<Book, Long> {
    void saveBook(BookDto bookDto);
    List<BookDto> findBooksByUserID(Long userID, BookStatusEnum statusEnum);
    BookDto findBook(Long id);
    void deleteBook(Long id);
    void updateBook(BookDto bookDto);
    void addBookToUserFavs(Long userID, Long bookID);
    void deleteBookFromUserFavs(Long userID, Long bookID);
    List<BookDto> getUserFavBooks(Long userID);
    List<BookDto> searchBooks(String value);
    List<BookDto> searchBooksExtended(BookSearchDto searchDto);

}
