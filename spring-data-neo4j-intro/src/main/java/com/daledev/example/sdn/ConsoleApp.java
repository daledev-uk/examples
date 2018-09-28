package com.daledev.example.sdn;

import com.daledev.example.sdn.console.ConsoleActionProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dale.ellis
 * @since 27/09/2018
 */
@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {
    private ConsoleActionProcessor consoleActionProcessor;

    /**
     * @param consoleActionProcessor
     */
    public ConsoleApp(ConsoleActionProcessor consoleActionProcessor) {
        this.consoleActionProcessor = consoleActionProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) {
        consoleActionProcessor.handleInput();
    }
}