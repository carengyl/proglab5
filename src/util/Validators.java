package util;

import exceptions.InvalidNumberOfArgsException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Validators {

    private Validators() {}

    public static void ValidateNumberOfArgs(String[] commandArgs, int numberOfArgs) throws InvalidNumberOfArgsException {
        if (commandArgs.length != numberOfArgs) {
            throw new InvalidNumberOfArgsException(numberOfArgs, commandArgs.length);
        }
    }

    public static String validateStringInput(String inputMessage, boolean nullable, Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage);
        String input;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("Данное поле не может быть пустым, повторите ввод");
                    continue;
                }
            } catch (NoSuchElementException e) {
                    OutputUtil.printErrorMessage(e.getMessage());
                    System.exit(1);
                    return null;
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
                    OutputUtil.printErrorMessage("Данное поле не может быть пустым, повторите ввод");
                    continue;
                }
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                System.exit(1);
                return null;
            }
            input = input.strip();
            switch (input.toLowerCase()) {
                case "y": return true;
                case "n": return false;
                default: OutputUtil.printErrorMessage("Введите y/n");
            }
        } while (true);
    }

    public static Integer validateEnumInput(String inputMessage, int enumLength, boolean nullable, Scanner scanner) {
        OutputUtil.printSuccessfulMessage(inputMessage);
        String input;
        int value;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("Данное поле не может быть пустым, повторите ввод");
                    continue;
                }
                value = Integer.parseInt(input);
                if (value > 0 && value <= enumLength) {
                    return value - 1;
                } else {
                    OutputUtil.printErrorMessage("Номер элемента не может быть меньше 0 или больше " + enumLength);
                }
            } catch (IllegalArgumentException e) {
                OutputUtil.printErrorMessage("Введите номер элемента:");
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                System.exit(1);
                return null;
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
        OutputUtil.printSuccessfulMessage(inputMessage);
        String input;
        T value;
        do {
            try {
                input = scanner.nextLine();
                if (input.equals("") && nullable) {
                    return null;
                } else if (input.equals("")) {
                    OutputUtil.printErrorMessage("Данное поле не может быть пустым, повторите ввод");
                    continue;
                }
                value = function.apply(input);
            } catch (IllegalArgumentException e) {
                OutputUtil.printErrorMessage(errorMessage);
                continue;
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                System.exit(1);
                return null;
            }
            if (predicate.test(value)) {
                return value;
            } else {
                OutputUtil.printErrorMessage(wrongCaseMessage);
            }
        } while(true);
    }
}
