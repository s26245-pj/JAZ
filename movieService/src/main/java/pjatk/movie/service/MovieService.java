package pjatk.movie.service;

import org.springframework.stereotype.Service;
import pjatk.movie.model.Movie;
import pjatk.movie.repository.MovieRepository;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovieById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }


}
