package com.daledev.example.sdn.config;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.daledev.example.sdn.service"})
@EnableNeo4jRepositories(basePackages = "com.daledev.example.sdn.repository")
@Profile({"embedded", "test"})
public class MovieDatabaseNeo4jTestConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new org.neo4j.ogm.config.Configuration.Builder().build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(getConfiguration(), "com.daledev.example.sdn.domain");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
