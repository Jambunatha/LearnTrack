package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.Scanner;

public class InputValidator {

    public static String readNonEmptyString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            throw new InvalidInputException("Input cannot be empty.");
        }
        return input;
    }

    public static String readOptionalString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number. Please enter a valid integer.");
        }
    }

    public static String readChoice(Scanner sc, String prompt, String... validOptions) {
        System.out.print(prompt + " (" + String.join("/", validOptions) + "): ");
        String input = sc.nextLine().trim().toUpperCase();

        for (String option : validOptions) {
            if (input.equals(option.toUpperCase())) {
                return option.toUpperCase();
            }
        }
        throw new InvalidInputException("Invalid choice. Allowed values: " + String.join(", ", validOptions));
    }
}
