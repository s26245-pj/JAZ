package com.pjatk.movieservice2.movie.controller;

import com.pjatk.movieservice2.movie.model.Movie;
import com.pjatk.movieservice2.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moviesDb")

public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @Operation(summary = "Get all movies from database", description = "Returns all movie")
    @ApiResponse(responseCode = "200", description = "Movie found",
            content   = {@Content(mediaType = "application/json"),
              @Schema(implementation = Media.class))
    )
    @GetMapping(value = "/movies")
    public ResponseEntity<List<Movie>> getMovieRepository() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/movies/{id}")
    @Operation(summary = "Find movie", description = "Finds  movie with the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie with provided id found"),
            @ApiResponse(responseCode = "404", description = "Movie with provided id not found")
    })
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if(movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movies")
    @Operation(summary = "Adds movie", description = "Add movie with provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie with provided data added"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if(movieService.isValid(movie)){
            movieService.addMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/movies/{id}")
    @Operation(summary = "Update movie", description = "Updating movie with provided id with  data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie with provided data added"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "404", description = "Movie to update not found")
    })
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie updatedMovie){
        Movie movieToUpdate = movieService.getMovieById(id);
        if (movieToUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        movieToUpdate.setTitle(updatedMovie.getTitle());
        movieToUpdate.setCategory(updatedMovie.getCategory());
        movieToUpdate.setDescription(updatedMovie.getDescription());
        movieToUpdate.setReleaseYear(updatedMovie.getReleaseYear());

        movieService.addMovie(movieToUpdate);

        return ResponseEntity.ok(movieToUpdate);
    }

    @DeleteMapping("/movies/{id}")
    @Operation(summary = "Delete a movie", description = "Deletes a movie with the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie with provided id deleted"),
            @ApiResponse(responseCode = "404", description = "Movie with provided id not found")
    })
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if(movie != null) {
            ResponseEntity.notFound().build();
        }
        movieService.deleteMovie(movie);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/movies/setUnavailable/{id}")
    @Operation(summary = "Set movie unavailable", description = "Setting movie as unavailable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie with provided set unavailable"),
            @ApiResponse(responseCode = "404", description = "Movie with provided id not found")
    })
    public ResponseEntity<Movie> setUnavailable(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            movieService.setToUnavailable(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
