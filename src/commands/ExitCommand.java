package commands;

import commandLine.CommandReader;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

import java.util.Scanner;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "Shut down the program (everything not saved will be lost)");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            boolean userInput = Validators.validateBooleanInput("Shut down",
                    new Scanner(System.in));
            if (userInput) {
                OutputUtil.printSuccessfulMessage("Shutting down...");
                CommandReader.togglePerformanceStatus();
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
