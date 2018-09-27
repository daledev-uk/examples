package com.daledev.example.sdn.service;

import com.daledev.example.sdn.domain.Person;
import com.daledev.example.sdn.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Transactional
@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     *
     * @param personName
     * @return
     */
    public Person createPerson(String personName) {
        Person mark = new Person();
        mark.setName(personName);
        personRepository.save(mark);
        return mark;
    }

}
