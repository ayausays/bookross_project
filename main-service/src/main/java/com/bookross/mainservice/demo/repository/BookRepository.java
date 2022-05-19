package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseRepository<Book, Long>{

}
