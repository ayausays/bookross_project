package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.AppUser;
import com.bookross.mainservice.demo.entity.Book;
import com.bookross.mainservice.demo.entity.BookStatusEnum;
import com.bookross.mainservice.demo.entity.Genre;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.exception.EntityNotFoundException;
import com.bookross.mainservice.demo.repository.BookRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import com.bookross.mainservice.demo.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends BaseServiceImpl<Book, Long, BookRepository> implements BookService {

    private final AppUserService appUserService;
    private final GenreService genreService;

    @Override
    public void saveBook(BookDto bookDto) {
        Book book = new Book();
        AppUser appUser = appUserService.findOrThrowNotFound(bookDto.getUserID());
        book.setUser(appUser);
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setStatus(BookStatusEnum.AVAILABLE.getValue());
        setBookGenres(bookDto.getGenres(), book);
        getRepository().save(book);
    }

    @Override
    public List<BookDto> findBooksByUserID(Long userID) {
        List<Book> books = getRepository().findBooksByUserID(userID);
        List<BookDto> bookDtos = new ArrayList<>();
        books.forEach(book -> {
            BookDto bookDto = convertToBookDto(book);
            bookDtos.add(bookDto);
        });
        return bookDtos;
    }

    @Override
    public BookDto findBook(Long id) {
        Book book =  findOrThrowNotFound(id);
        return convertToBookDto(book);
    }

    private BookDto convertToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setUserID(book.getUser().getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setStatus(BookStatusEnum.valueOf(book.getStatus()));
        bookDto.setTitle(book.getTitle());
        bookDto.setGenres(book.getGenres().stream()
                .map(Genre::getGenre)
                .collect(Collectors.toList()));
        return bookDto;
    }

    private void setBookGenres(List<String> genresList, Book book){
        List<Genre> bookGenres = new ArrayList<>();
        genresList.forEach(genreValue -> {
            Genre genre = genreService.findGenreByGenreName(genreValue);
            if (genre == null) throw new EntityNotFoundException(Genre.class, "genre is not valid. ", genreValue);
            genre.getBooks().add(book);
            bookGenres.add(genre);
        });
        book.setGenres(bookGenres);
    }

    @Override
    public void updateBook(Long id, String title, String author, String status, String[] genres) {
        Book book = findOrThrowNotFound(id);
        if (title != null) {
            book.setTitle(title);
        }
        if (author != null) {
            book.setAuthor(author);
        }
        if (status != null) {
            book.setStatus(status);
        }
        if (genres != null && genres.length != 0) {
            setBookGenres(Arrays.asList(genres), book);
        }
        getRepository().save(book);
    }

    @Override
    public void deleteBook(Long id) {
        getRepository().delete(findOrThrowNotFound(id));
    }
}
