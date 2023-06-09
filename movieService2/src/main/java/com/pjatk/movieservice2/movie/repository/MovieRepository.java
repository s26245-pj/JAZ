package com.pjatk.movieservice2.movie.repository;

import com.pjatk.movieservice2.movie.enums.Category;
import com.pjatk.movieservice2.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByCategory(Category category);
    List<Movie> findByReleaseYear(int releaseYear);


    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.isAvailable = true WHERE m.id = :id")
    void setToUnavailable(@Param("id") int id);
}
