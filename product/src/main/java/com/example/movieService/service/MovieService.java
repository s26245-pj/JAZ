package com.example.movieService.service;

import com.example.movieService.model.Movie;
import com.example.movieService.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie saveProduct(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> getProducts() {
        return repository.findAll();
    }



}