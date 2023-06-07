package pjatk.movie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    @Enumerated(EnumType.STRING)
    private String category;
    @Column
    private String description;
    @Column
    private int releaseYear;
    @Column
    private boolean isAvailable;

}
