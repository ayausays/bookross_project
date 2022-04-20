package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends BaseRepository<Book, Long>{

    @Query("select b from Book b where b.user.id = ?1")
    List<Book> findBooksByUserID(Long userID);

}
