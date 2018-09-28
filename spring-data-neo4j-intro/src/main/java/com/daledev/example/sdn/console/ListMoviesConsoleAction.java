package com.daledev.example.sdn.console;

import com.daledev.example.sdn.domain.Movie;
import com.daledev.example.sdn.domain.Person;
import com.daledev.example.sdn.service.MovieService;
import com.daledev.example.sdn.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 28/09/2018
 */
@Service
public class ListMoviesConsoleAction implements ConsoleAction {
    private MovieService movieService;

    public ListMoviesConsoleAction(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String getActionText() {
        return "List all movies";
    }

    @Override
    public void handleAction(Scanner scanner) {
        for (Movie movie: movieService.getAllMovies()) {
            System.out.printf("\t[%s] - %s (%s)%n", movie.getId(), movie.getTitle(), movie.getReleased());
        }
        System.out.println("\n");
    }
}
