package com.pjatk.movie.controller;

import com.pjatk.movie.model.Movie;
import com.pjatk.movie.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = "/kurwa")
    public List<Movie> getMovieRepository() {
        return movieRepository.findAll();
    }
}
