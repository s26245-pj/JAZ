package pjatk.jaz.movie.service;

import org.springframework.stereotype.Service;
import pjatk.jaz.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void updateMovie(Integer id, Movie updatedMovie) {
        Optional<Movie> movieToUpdate = Optional.ofNullable(getMovieById(id));
        if (movieToUpdate.isPresent()) {
            Movie movie = movieToUpdate.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setCategory(updatedMovie.getCategory());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
        } else {
            throw new IllegalArgumentException("Film with id " + id + " doesn't exist");
        }
    }




    public void deleteMovie(Integer id) {
        Optional<Movie> movieToDelete = Optional.ofNullable(getMovieById(id));
        if(movieToDelete.isPresent()) {
            movies.removeIf(film -> film.getId() == (id));
        } else {
            throw new IllegalArgumentException("Movie with id " + id + "don't exist");
        }
    }
}
