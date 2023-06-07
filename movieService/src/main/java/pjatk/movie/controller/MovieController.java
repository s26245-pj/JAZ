package pjatk.movie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.movie.model.Movie;
import pjatk.movie.service.MovieService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movieService")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }
/*
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        try {
            movieService.updateMovie(id, updatedMovie);
            return ResponseEntity.ok(updatedMovie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovieById(id);
    }

    @PutMapping("/movies/{id}/available")
    public ResponseEntity<Movie> markMovieAsAvailable(@PathVariable Long id) {
        Optional<Movie> optionalMovie = Optional.ofNullable(movieService.getMovieById(id));
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setAvailable(true);
            movieService.saveMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
