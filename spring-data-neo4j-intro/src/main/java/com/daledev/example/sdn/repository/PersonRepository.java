package com.daledev.example.sdn.repository;

import com.daledev.example.sdn.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
}
