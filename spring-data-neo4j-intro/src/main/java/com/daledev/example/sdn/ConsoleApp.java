package com.daledev.example.sdn;

import com.daledev.example.sdn.console.ConsoleAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private List<ConsoleAction> consoleActions;

    public static void main(String[] args) {
        System.out.println("STARTING THE APPLICATION");
        SpringApplication.run(ConsoleApp.class, args);
        System.out.println("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        System.out.println("EXECUTING : command line runner");
        Scanner scanner = new Scanner(System.in);

        boolean requestAnotherAction;
        do {
            requestAnotherAction = askWhatAction(scanner);
        } while (requestAnotherAction);
    }

    private boolean askWhatAction(Scanner scanner) {
        System.out.println("What action do you want to perform?");
        for (int i = 0; i < consoleActions.size(); i++) {
            System.out.printf("[%s] - %s%n", i, consoleActions.get(i).getActionText());
        }
        System.out.print("Select Option : ");

        // get their input as a String
        if (scanner.hasNextInt()) {
            int selection = scanner.nextInt();
            if (selection >= 0 && selection < consoleActions.size()) {
                consoleActions.get(selection).handleAction(scanner);
                return true;
            }
        }
        return false;
    }
}