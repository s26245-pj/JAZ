package com.pjatk.movieservice2.movie.service;

import com.pjatk.movieservice2.movie.enums.Category;
import com.pjatk.movieservice2.movie.model.Movie;
import com.pjatk.movieservice2.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean isValid(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            return false;
        }
        if (movie.getCategory() == null) {
            return false;
        }
        if (movie.getDescription() == null || movie.getDescription().isEmpty()) {
            return false;
        }
        if (movie.getReleaseYear() <= 0) {
            return false;
        }
        return true;
    }


    public Movie getMovieById(int id) {
        Optional<Movie> optionalMovie = movieRepository.findById((long) id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new RuntimeException();
        }
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
    public List<Movie> getMoviesByCategory(Category category) {
        return movieRepository.findByCategory(category);
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    public void setToUnavailable(int id) {
        movieRepository.setToUnavailable(id);
    }




}
