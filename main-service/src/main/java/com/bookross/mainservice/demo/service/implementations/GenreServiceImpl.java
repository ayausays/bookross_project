package com.bookross.mainservice.demo.service.implementations;

import com.bookross.mainservice.demo.entity.Genre;
import com.bookross.mainservice.demo.repository.GenreRepository;
import com.bookross.mainservice.demo.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl extends BaseServiceImpl<Genre, Long, GenreRepository>
        implements GenreService {
    @Override
    public Genre findGenreByGenreName(String genreName) {
        return getRepository().findGenreByGenre(genreName);
    }
}
