package util;

import exceptions.InvalidNumberOfArgs;

public final class Validators {

    private Validators() {}

    public static void ValidateNumberOfArgs(String[] commandArgs, int numberOfArgs) throws InvalidNumberOfArgs {
        if (commandArgs.length != numberOfArgs) {
            throw new InvalidNumberOfArgs(numberOfArgs, commandArgs.length);
        }
    }

}
