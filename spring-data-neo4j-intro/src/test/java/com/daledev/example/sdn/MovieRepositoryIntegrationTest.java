package com.daledev.example.sdn;

import com.daledev.example.sdn.config.MovieDatabaseNeo4jTestConfiguration;
import com.daledev.example.sdn.domain.Movie;
import com.daledev.example.sdn.domain.Person;
import com.daledev.example.sdn.domain.Role;
import com.daledev.example.sdn.repository.MovieRepository;
import com.daledev.example.sdn.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MovieDatabaseNeo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class MovieRepositoryIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void initializeDatabase() {
        System.out.println("seeding embedded database");

        Movie italianJob = persistMovie("The Italian Job", 1999);
        Person markWahlberg = persistPerson("Mark Wahlberg");
        persistRole(italianJob, markWahlberg, "Charlie Croker");
    }

    private Person persistPerson(String personName) {
        Person mark = new Person();
        mark.setName(personName);
        personRepository.save(mark);
        return mark;
    }

    private Movie persistMovie(String title, int released) {
        Movie italianJob = new Movie();
        italianJob.setTitle(title);
        italianJob.setReleased(released);
        movieRepository.save(italianJob);
        return italianJob;
    }

    private Role persistRole(Movie movie, Person person, String... roleNames) {
        Role role = new Role();
        role.setMovie(movie);
        role.setPerson(person);
        role.setRoles(Arrays.asList(roleNames));
        movie.setRoles(Arrays.asList(role));
        movieRepository.save(movie);
        return role;
    }

    @Test
    @DirtiesContext
    public void testFindByTitle() {
        System.out.println("findByTitle");
        String title = "The Italian Job";
        Movie result = movieRepository.findByTitle(title);
        assertNotNull(result);
        assertEquals(1999, result.getReleased());
    }

    @Test
    @DirtiesContext
    public void testCount() {
        System.out.println("count");
        long movieCount = movieRepository.count();
        assertNotNull(movieCount);
        assertEquals(1, movieCount);
    }

    @Test
    @DirtiesContext
    public void testFindAll() {
        System.out.println("findAll");
        Collection<Movie> result = (Collection<Movie>) movieRepository.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DirtiesContext
    public void testFindByTitleContaining() {
        System.out.println("findByTitleContaining");
        String title = "Italian";
        Collection<Movie> result = movieRepository.findByTitleContaining(title);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DirtiesContext
    public void testGraph() {
        System.out.println("graph");
        List<Map<String, Object>> graph = movieRepository.graph(5);
        assertEquals(1, graph.size());
        Map<String, Object> map = graph.get(0);
        assertEquals(2, map.size());
        String[] cast = (String[]) map.get("cast");
        String movie = (String) map.get("movie");
        assertEquals("The Italian Job", movie);
        assertEquals("Mark Wahlberg", cast[0]);
    }

    @Test
    @DirtiesContext
    public void testDeleteMovie() {
        System.out.println("deleteMovie");
        movieRepository.delete(movieRepository.findByTitle("The Italian Job"));
        assertNull(movieRepository.findByTitle("The Italian Job"));
    }

    @Test
    @DirtiesContext
    public void testDeleteAll() {
        System.out.println("deleteAll");
        movieRepository.deleteAll();
        Collection<Movie> result = (Collection<Movie>) movieRepository.findAll();
        assertEquals(0, result.size());
    }
}
