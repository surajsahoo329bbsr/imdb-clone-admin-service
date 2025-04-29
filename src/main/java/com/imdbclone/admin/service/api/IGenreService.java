package com.imdbclone.admin.service.api;

import com.imdbclone.admin.entity.Genre;

import java.util.List;

public interface IGenreService {

    List<String> getAllGenres(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst);

    void addGenre(String name);

    Genre getGenreById(Long id);

    Genre updateGenreById(Long id, String name);
}