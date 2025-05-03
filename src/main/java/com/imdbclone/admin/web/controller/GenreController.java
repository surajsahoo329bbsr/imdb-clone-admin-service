package com.imdbclone.admin.web.controller;

import com.imdbclone.admin.aspect.annotation.SetRequestAttributes;
import com.imdbclone.admin.entity.Genre;
import com.imdbclone.admin.service.api.IAdminService;
import com.imdbclone.admin.service.api.IGenreService;
import com.imdbclone.admin.web.response.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genres ")
@Tag(name = "Genre Management", description = "APIs for managing genres, including listing, adding, fetching, and updating genres")
public class GenreController {

    private final IGenreService genreService;

    @Autowired
    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @SetRequestAttributes
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all genres",
            description = "Fetches a paginated list of all genres. Supports sorting by latest created first or oldest first."
    )
    public ResponseEntity<?> getAllGenres(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam boolean sortByLatestFirst) {
        List<String> genres = genreService.getAllGenres(pageNumber, pageSize, sortByLatestFirst);
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @SetRequestAttributes
    @PostMapping(path = "/{name}")
    @Operation(
            summary = "Add a new genre",
            description = "Creates a new genre with the provided name. Returns success if the genre is added."
    )
    public ResponseEntity<?> addGenre(@PathVariable(name = "name") String name) {
        genreService.addGenre(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @SetRequestAttributes
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get genre by ID",
            description = "Fetches the details of a genre using its unique ID."
    )
    public ResponseEntity<?> getGenreById(@PathVariable(name = "id") Long id) {
        Genre genre = genreService.getGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @SetRequestAttributes
    @PatchMapping(path = "/update/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update genre by ID",
            description = "Updates the name of an existing genre using its unique ID. Returns the updated genre object."
    )
    public ResponseEntity<?> updateGenreById(@RequestParam Long id, @RequestParam String name) {
        Genre genre = genreService.updateGenreById(id, name);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

}
