package com.pjatk.movie.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Enumerated(EnumType.STRING)
    private String category;
    @Column
    private String description;
    @Column
    private int releaseYear;
}
