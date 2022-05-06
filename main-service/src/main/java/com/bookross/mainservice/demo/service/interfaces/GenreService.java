package com.bookross.mainservice.demo.service.interfaces;

import com.bookross.mainservice.demo.entity.Genre;

public interface GenreService {
    Genre findGenreByGenreName(String genreName);
}
