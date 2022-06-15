package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.*;
import com.bookross.mainservice.demo.entity.request.BookDto;
import com.bookross.mainservice.demo.entity.request.BookSearchDto;
import com.bookross.mainservice.demo.exception.EntityNotFoundException;
import com.bookross.mainservice.demo.repository.BookRepository;
import com.bookross.mainservice.demo.service.interfaces.AppUserService;
import com.bookross.mainservice.demo.service.interfaces.BookService;
import com.bookross.mainservice.demo.service.interfaces.GenreService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends BaseServiceImpl<Book, Long, BookRepository> implements BookService {

    private final AppUserService appUserService;
    private final GenreService genreService;

    private final EntityManager entityManager;

    @Override
    public Long saveBook(BookDto bookDto) {
        Book book = new Book();
        AppUser appUser = appUserService.findOrThrowNotFound(bookDto.getUserID());
        book.setUser(appUser);
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setStatus(BookStatusEnum.AVAILABLE.getCode());
        book.setDescription(bookDto.getDescription());
        book.setDateOfAdd(LocalDateTime.now());
        book.setPageAmount(bookDto.getPageAmount());
        book.setYear(bookDto.getYear());
        setBookGenres(bookDto.getGenres(), book);
        book = getRepository().save(book);

        appUser.getBooks().add(book);
        appUserService.save(appUser);

        return book.getId();
    }

    @Override
    public List<BookDto> findBooksByUserID(Long userID, BookStatusEnum statusEnum) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        List<BookDto> bookDtos = new ArrayList<>();
        appUser.getBooks().stream()
                .filter(book -> book.getStatus().equals(statusEnum.getCode()))
                .forEach(book -> {
                    BookDto bookDto = convertToBookDto(book);
                    setImgData(bookDto, book);
                    bookDtos.add(bookDto);
                });
        return bookDtos;
    }

    @Override
    public BookDto findBook(Long id) {
        Book book = findOrThrowNotFound(id);
        BookDto bookDto = convertToBookDto(book);
        setImgData(bookDto, book);
        return bookDto;
    }

    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setUserID(book.getUser().getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setStatus(BookStatusEnum.valueOf(book.getStatus()));
        bookDto.setDateOfAdd(book.getDateOfAdd());
        bookDto.setPageAmount(book.getPageAmount());
        bookDto.setYear(book.getYear());
        bookDto.setTitle(book.getTitle());
        bookDto.setUserFIO(book.getUser().getFirstName() + " " + book.getUser().getLastName());
        bookDto.setDescription(book.getDescription());
        //book.getImagePath().
        bookDto.setBookImagePath(book.getImagePath());
        bookDto.setGenres(book.getGenres().stream()
                .map(Genre::getGenre)
                .collect(Collectors.toList()));
        return bookDto;
    }

    public void setImgData(BookDto bookDto, Book book){
        if (book.getImagePath() != null && !(book.getImagePath().trim().length()<1)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(book.getImagePath()));
                String s = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(bytes);
                bookDto.setImgData(s);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setBookGenres(List<String> genresList, Book book) {
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
    public void updateBook(BookDto bookDto) {
        Book book = findOrThrowNotFound(bookDto.getId());
        if (bookDto.getTitle() != null) {
            book.setTitle(bookDto.getTitle());
        }
        if (bookDto.getAuthor() != null) {
            book.setAuthor(bookDto.getAuthor());
        }
        if (bookDto.getStatus() != null) {
            book.setStatus(bookDto.getStatus().getCode());
        }
        if (bookDto.getGenres() != null && bookDto.getGenres().size() != 0) {
            setBookGenres(bookDto.getGenres(), book);
        }
        if (bookDto.getDescription() != null) {
            book.setDescription(bookDto.getDescription());
        }
        if (bookDto.getPageAmount() != 0) {
            book.setPageAmount(bookDto.getPageAmount());
        }
        if (bookDto.getYear() != null) {
            book.setYear(bookDto.getYear());
        }
        getRepository().save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = findOrThrowNotFound(id);
        // todo: delete book image from folder
        book.getUsersAddedToFavs().forEach(user -> {
            Set<Book> books = user.getFavoriteBooks();
            books.remove(book);
            user.setFavoriteBooks(books);
            appUserService.save(user);
        });
        getRepository().delete(book);
    }

    @Override
    public void addBookToUserFavs(Long userID, Long bookID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        Book book = findOrThrowNotFound(bookID);
        Set<Book> books = appUser.getFavoriteBooks();
        books.add(book);
        appUser.setFavoriteBooks(books);

        Set<AppUser> users = book.getUsersAddedToFavs();
        users.add(appUser);
        book.setUsersAddedToFavs(users);

        appUserService.save(appUser);
        save(book);
    }

    @Override
    public void deleteBookFromUserFavs(Long userID, Long bookID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        Book book = findOrThrowNotFound(bookID);

        Set<Book> books = appUser.getFavoriteBooks();
        books.remove(book);
        appUser.setFavoriteBooks(books);

        Set<AppUser> users = book.getUsersAddedToFavs();
        users.remove(appUser);
        book.setUsersAddedToFavs(users);

        appUserService.save(appUser);
        save(book);
    }

    @Override
    public List<BookDto> getUserFavBooks(Long userID) {
        AppUser appUser = appUserService.findOrThrowNotFound(userID);
        List<BookDto> bookDtos = new ArrayList<>();
        appUser.getFavoriteBooks()
                .forEach(book -> {
                            BookDto bookDto = convertToBookDto(book);
                            bookDtos.add(bookDto);
                        }
                );
        return bookDtos;
    }

    @Override
    public List<BookDto> searchBooks(String value){
        List<Book> allBooks = getAll();
        return allBooks.stream()
                .filter(book -> book.getStatus().equals(BookStatusEnum.AVAILABLE.getCode()))
                .filter(book -> (book.getTitle().toLowerCase().contains(value.toLowerCase().trim())))
                .map(this::convertToBookDto)
                .sorted(Comparator.comparing(BookDto::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> searchBooksExtended(BookSearchDto searchDto) {
        JPAQuery<BookDto> query = new JPAQuery<>(entityManager);
        QBook qBook = QBook.book;
        BooleanBuilder whereBuilder = createWhereClause(searchDto, qBook);
        List<Book> books = query.select(qBook).distinct().from(qBook).where(whereBuilder).fetch();
        return books.stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    private BooleanBuilder createWhereClause(BookSearchDto searchDto, QBook qBook){
        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(qBook.status.eq(BookStatusEnum.AVAILABLE.getCode()));
        if (searchDto.getAuthor() !=null){
            whereBuilder.and(qBook.author.toLowerCase().contains(searchDto.getAuthor().toLowerCase().trim()));
        }
        if (searchDto.getTitle() !=null){
            whereBuilder.and(qBook.title.toLowerCase().contains(searchDto.getTitle().toLowerCase().trim()));
        }
        if (searchDto.getPageFrom() != 0){
            whereBuilder.and(qBook.pageAmount.goe(searchDto.getPageFrom()));
        }
        if (searchDto.getPageTo() != 0){
            whereBuilder.and(qBook.pageAmount.loe(searchDto.getPageTo()));
        }
        if (searchDto.getYearFrom() != null){
            whereBuilder.and(qBook.year.goe(searchDto.getYearFrom()));
        }
        if (searchDto.getYearTo() != null){
            whereBuilder.and(qBook.year.loe(searchDto.getYearTo()));
        }
        if (searchDto.getGenres() != null){
            List<Genre> genres = searchDto.getGenres().stream()
                    .map(genreService::findGenreByGenreName)
                    .collect(Collectors.toList());
            whereBuilder.and(qBook.genres.any().in(genres));
        }
        return whereBuilder;
    }

    @Override
    public List<BookDto> getPopularBooks() {
        return getRepository().getPopularBooks().stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getNewBooks() {
        return getRepository().getNewBooks().stream().map(this::convertToBookDto).collect(Collectors.toList());
    }
}
