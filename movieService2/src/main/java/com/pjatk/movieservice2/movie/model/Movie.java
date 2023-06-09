package com.pjatk.movieservice2.movie.model;

import com.pjatk.movieservice2.movie.enums.Category;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@NamedQueries({
    @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
    @NamedQuery(name = "Movie.findByCategory", query = "SELECT m FROM Movie m WHERE m.category = :category"),
    @NamedQuery(name = "Movie.findByReleaseYear", query = "SELECT m FROM Movie m WHERE m.releaseYear = :releaseYear")
})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private int releaseYear;
    private boolean isAvailable;
}
