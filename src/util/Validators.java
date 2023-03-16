package util;

import exceptions.InvalidNumberOfArgsException;
import exceptions.WrongArgTypeException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Validators {

    private Validators() {}

    public static void validateNumberOfArgs(String[] commandArgs, int numberOfArgs) throws InvalidNumberOfArgsException {
        if (commandArgs.length != numberOfArgs) {
            throw new InvalidNumberOfArgsException(numberOfArgs, commandArgs.length);
        }
    }

    public static String validateStringInput(String inputMessage, boolean nullable, Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage + ((nullable) ? " (press Enter to skip):": ":"));
        String input;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("This variable can't be null, try again:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                    OutputUtil.printErrorMessage(e.getMessage());
                    continue;
            }
            return input;
        } while (true);
    }

    public static Boolean validateBooleanInput(String inputMessage, Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage + ", y/n? ");
        String input;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("")) {
                    OutputUtil.printErrorMessage("This variable can't be null, try again:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                continue;
            }
            input = input.strip();
            switch (input.toLowerCase()) {
                case "y": return true;
                case "n": return false;
                default: OutputUtil.printErrorMessage("Type 'y' or 'n', where 'y' is for 'yes, 'n' is for 'no'");
            }
        } while (true);
    }

    public static Integer validateEnumInput(String inputMessage, int enumLength, boolean nullable, Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage);
        OutputUtil.printSuccessfulMessage("Pick a number" + ((nullable) ? " or press Enter to skip: ": ": "));
        String input;
        int value;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("This variable can't be null, try again:");
                    continue;
                }
                value = Integer.parseInt(input);
                if (value > 0 && value <= enumLength) {
                    return value - 1;
                } else {
                    OutputUtil.printErrorMessage("Pick an Enum element number, it can't be less than 0 and greater than " + enumLength);
                }
            } catch (IllegalArgumentException e) {
                OutputUtil.printErrorMessage("Pick a number: ");
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
            }
        } while (true);
    }

    public static <T> T validateInput(Predicate<Object> predicate,
                                      String inputMessage,
                                      String errorMessage,
                                      String wrongCaseMessage,
                                      Function<String, T> function,
                                      Boolean nullable,
                                      Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage + ((nullable) ? " (press Enter to skip):": ":"));
        String input;
        T value;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("This variable can't be hull, try again:");
                    continue;
                }
                value = function.apply(input);
            } catch (IllegalArgumentException e) {
                OutputUtil.printErrorMessage(errorMessage);
                continue;
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                continue;
            }
            if (predicate.test(value)) {
                return value;
            } else {
                OutputUtil.printErrorMessage(wrongCaseMessage);
            }
        } while(true);
    }

    public static <T> T validateArg(Predicate<Object> predicate,
                                    String wrongCaseMessage,
                                    Function<String, T> function,
                                    String arg) throws WrongArgTypeException, IllegalArgumentException {
        T value = function.apply(arg);
        if (predicate.test(value)) {
            return value;
        } else {
            throw new WrongArgTypeException(wrongCaseMessage);
        }
    }
}
