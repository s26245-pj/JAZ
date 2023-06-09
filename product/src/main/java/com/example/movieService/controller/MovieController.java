package com.example.movieService.controller;

import com.example.movieService.model.Movie;
import com.example.movieService.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping("/products")
    public Movie addProduct(@RequestBody Movie movie) {
        return service.saveProduct(movie);
    }

    @GetMapping("/elo")
    public List<Movie> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("")
    public String goddammit() {
        return "welcome";
    }


}