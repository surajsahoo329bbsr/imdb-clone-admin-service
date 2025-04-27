package com.imdbclone.admin.repository;

import com.imdbclone.admin.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
