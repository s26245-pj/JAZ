package com.pjatk.movieservice2.movie.controller;

import com.pjatk.movieservice2.movie.model.Movie;
import com.pjatk.movieservice2.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moviesDb")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<Movie>> getMovieRepository() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if(movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if(movieService.isValid(movie)){
            movieService.addMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/movies/{id}")
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
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if(movie != null) {
            ResponseEntity.notFound().build();
        }
        movieService.deleteMovie(movie);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/movies/setUnavailable/{id}")
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
