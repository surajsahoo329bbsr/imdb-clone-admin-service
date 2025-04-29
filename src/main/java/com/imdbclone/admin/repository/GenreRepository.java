package com.imdbclone.admin.repository;

import com.imdbclone.admin.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, PagingAndSortingRepository<Genre, Long> {

}
