package com.bookross.mainservice.demo.service.implementations;


import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.repository.BookRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl extends BaseServiceImpl<Book, Long, BookRepository> implements BookService {
    @Autowired
    private final AppUserService appUserService;

    @Override
    public void saveBook(BookDto bookDto) {
        Book book = new Book();
        AppUser appUser = appUserService.findOrThrowNotFound(bookDto.getUserID());
        book.setUser(appUser);
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setStatus(bookDto.getStatus());
        getRepository().save(book);
    }

    @Override
    public List<Book> findBooksByUserID(Long userID){
        return getRepository().findBooksByUserID(userID);
    }

    @Override
    public Book findBook(Long id){
        return findOrThrowNotFound(id);
    }

    @Override
    public void updateBook(Long id, String title, String author, String status) {
        Book book = findOrThrowNotFound(id);
        if (title != null){
            book.setTitle(title);
        }
        if (author != null){
            book.setAuthor(author);
        }
        if (status != null){
            book.setStatus(status);
        }
        getRepository().save(book);
    }

    @Override
    public void deleteBook(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }
}
