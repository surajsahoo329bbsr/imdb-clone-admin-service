package com.imdbclone.admin.service.implementation;

import com.imdbclone.admin.entity.Admin;
import com.imdbclone.admin.entity.Genre;
import com.imdbclone.admin.repository.AdminRepository;
import com.imdbclone.admin.repository.GenreRepository;
import com.imdbclone.admin.service.api.IGenreService;
import com.imdbclone.admin.util.JWTUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GenreServiceImpl implements IGenreService {

    private final GenreRepository genreRepository;
    private final AdminRepository adminRepository;
    private final JWTUtils jwtUtils;

    public GenreServiceImpl(GenreRepository genreRepository, AdminRepository adminRepository, JWTUtils jwtUtils) {
        this.genreRepository = genreRepository;
        this.adminRepository = adminRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public List<String> getAllGenres(Integer pageNumber, Integer pageSize, boolean sortByLatestFirst) {
        Sort sort = sortByLatestFirst ? Sort.by("id").descending() :
                Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return genreRepository.findAll(pageable)
                .stream().map(Genre::getName).toList();
    }

    @Override
    @Transactional
    public void addGenre(String name) {
        Long adminId = jwtUtils.getAdminIdFromJwt();
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Invalid Admin/Admin Not Found"));
        Genre genre = Genre.builder()
                .name(name)
                .admin(admin)
                .createdAt(LocalDateTime.now())
                .createdBy(adminId)
                .build();
        genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre ID Not Found"));
    }

    @Override
    @Transactional
    public Genre updateGenreById(Long id, String name) {
        Genre fetchedGenre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre ID Not Found"));
        Long adminId = jwtUtils.getAdminIdFromJwt();
        adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Invalid Admin/Admin Not Found"));

        fetchedGenre.setName(name);
        fetchedGenre.setUpdatedAt(LocalDateTime.now());
        fetchedGenre.setUpdatedBy(adminId);
        genreRepository.save(fetchedGenre);

        return fetchedGenre;
    }
}
