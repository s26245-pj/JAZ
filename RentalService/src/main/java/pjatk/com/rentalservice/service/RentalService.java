package pjatk.com.rentalservice.service;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.com.rentalservice.model.Movie;


@Service
public class RentalService {
    private final RestTemplate restTemplate;
    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String movieServiceUrl = "http://localhost:8080/moviesDb/movies/";

    public Movie getMovie(int id) {
        return restTemplate.getForObject(movieServiceUrl + id, Movie.class);
    }

    public void returnMovie(int id) {
        restTemplate.put(movieServiceUrl + "setAvailable/" + id, HttpEntity.EMPTY);
    }

    public void rentMovie(int id) {
        restTemplate.put(movieServiceUrl + "setUnavailable/" + id, HttpEntity.EMPTY);
    }

}
