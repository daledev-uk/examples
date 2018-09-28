package com.daledev.example.sdn.console;

import com.daledev.example.sdn.domain.Movie;
import com.daledev.example.sdn.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Service
public class CreateMovieConsoleAction implements ConsoleAction {
    private MovieService movieService;

    public CreateMovieConsoleAction(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String getActionText() {
        return "Create Movie";
    }

    @Override
    public void handleAction(Scanner scanner) {
        System.out.println("Enter Movie Name : ");
        String movieName = scanner.next();
        System.out.println("Enter Movie Release Date : ");
        int releaseDate = scanner.nextInt();
        Movie movie = movieService.createMovie(movieName, releaseDate);
        System.out.printf("\tCreate Movie '%s', ID : %s%n", movie.getTitle(), movie.getId());
        System.out.println("\n");
    }
}
