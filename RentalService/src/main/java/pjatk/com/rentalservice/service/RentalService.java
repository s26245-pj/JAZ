package pjatk.com.rentalservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.com.rentalservice.model.Movie;


@Service
public class RentalService {
    private final RestTemplate restTemplate;
    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String movieServiceUrl = "http://localhost:8080/movieService/movies/";

    public Movie getMovie(Long id) {
        return restTemplate.getForObject(movieServiceUrl + id, Movie.class);
    }

    /*
    public Movie returnMovie(Long id) {

    }*/



}
