package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book, Long>{

    @Query("select b from Book b where b.user.id = ?1")
    List<Book> findBooksByUserID(Long userID);

}
