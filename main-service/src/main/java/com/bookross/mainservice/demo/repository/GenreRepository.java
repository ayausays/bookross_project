package com.bookross.mainservice.demo.repository;

import com.bookross.mainservice.demo.entity.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long>{
    Genre findGenreByGenre(String genre);
}
