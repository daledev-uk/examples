package com.daledev.example.sdn.console;

import com.daledev.example.sdn.domain.Person;
import com.daledev.example.sdn.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 28/09/2018
 */
@Service
public class ListPeopleConsoleAction implements ConsoleAction {
    private PersonService personService;

    public ListPeopleConsoleAction(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public String getActionText() {
        return "List all people";
    }

    @Override
    public void handleAction(Scanner scanner) {
        for (Person person : personService.getAllPeople()) {
            System.out.printf("\t[%s] - %s%n", person.getId(), person.getName());
        }
        System.out.println("\n");
    }
}
