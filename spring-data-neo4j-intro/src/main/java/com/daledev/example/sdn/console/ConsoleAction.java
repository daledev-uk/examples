package com.daledev.example.sdn.console;

import java.util.Scanner;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
public interface ConsoleAction {
    /**
     *
     * @return
     */
    String getActionText();

    /**
     *
     * @param scanner
     */
    void handleAction(Scanner scanner);
}
