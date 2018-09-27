package com.daledev.example.sdn.console;

import com.daledev.example.sdn.domain.Person;
import com.daledev.example.sdn.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@Service
public class CreatePersonConsoleAction implements ConsoleAction {
    private PersonService personService;

    public CreatePersonConsoleAction(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public String getActionText() {
        return "Create Person";
    }

    @Override
    public void handleAction(Scanner scanner) {
        System.out.print("Enter Persons Name : ");
        String personName = scanner.next();
        Person person = personService.createPerson(personName);
        System.out.printf("Create Person '%s', ID : %s%n", person.getName(), person.getId());
    }
}
