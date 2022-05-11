package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Genre;
import com.bookross.mainservice.demo.repository.GenreRepository;
import com.bookross.mainservice.demo.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl extends BaseServiceImpl<Genre, Long, GenreRepository>
        implements GenreService {
    @Override
    public Genre findGenreByGenreName(String genreName) {
        return getRepository().findGenreByGenre(genreName);
    }

    @Override
    public List<String> getAllGenres() {
        return getRepository().findAll().stream().map(Genre::getGenre).collect(Collectors.toList());
    }
}
