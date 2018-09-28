package com.daledev.example.sdn.console;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 28/09/2018
 */
@Component
public class ConsoleActionProcessor {
    private List<ConsoleAction> consoleActions;

    /**
     * @param consoleActions
     */
    public ConsoleActionProcessor(List<ConsoleAction> consoleActions) {
        this.consoleActions = consoleActions;
    }

    /**
     *
     */
    public void handleInput() {
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
        Optional<ConsoleAction> consoleAction = getConsoleAction(scanner);
        consoleAction.ifPresent(action -> action.handleAction(scanner));
        return consoleAction.isPresent();
    }

    private Optional<ConsoleAction> getConsoleAction(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int selection = scanner.nextInt();
            if (selection >= 0 && selection < consoleActions.size()) {
                return Optional.ofNullable(consoleActions.get(selection));
            }
        }
        return Optional.empty();
    }


}
