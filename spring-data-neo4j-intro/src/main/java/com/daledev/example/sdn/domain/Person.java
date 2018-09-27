package com.daledev.example.sdn.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import java.util.List;


/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    Long id;

    private String name;

    private int born;

    @Relationship(type = "ACTED_IN")
    private List<Movie> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
