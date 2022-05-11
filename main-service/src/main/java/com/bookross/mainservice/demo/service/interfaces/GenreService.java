package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre findGenreByGenreName(String genreName);
    List<String> getAllGenres();
}
