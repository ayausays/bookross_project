package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book, Long>{
    @Query(nativeQuery = true, value ="select b.* from users_fav_books u " +
            "left join book b on u.book_id = b.id " +
            "group by u.book_id " +
            "order by count(u.book_id) desc limit 20;")
    List<Book> getPopularBooks();

    @Query(nativeQuery = true, value ="select * from book order by book.date_of_add desc limit 20;")
    List<Book> getNewBooks();

}
