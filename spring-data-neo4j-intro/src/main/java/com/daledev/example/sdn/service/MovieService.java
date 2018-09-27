package com.daledev.example.sdn.service;

import com.daledev.example.sdn.domain.Movie;
import com.daledev.example.sdn.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     *
     * @param title
     * @param released
     * @return
     */
    public Movie persistMovie(String title, int released) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setReleased(released);
        movieRepository.save(movie);
        return movie;
    }
}
