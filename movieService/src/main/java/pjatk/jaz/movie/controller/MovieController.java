package pjatk.jaz.movie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.jaz.movie.model.Movie;
import pjatk.jaz.movie.service.MovieService;

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
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = Optional.ofNullable(movieService.getMovieById(id));
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (movie == null) {
            return ResponseEntity.badRequest().build();
        }
        movieService.addMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        try {
            movieService.updateMovie(id, updatedMovie);
            return ResponseEntity.ok(updatedMovie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Integer id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }







}
