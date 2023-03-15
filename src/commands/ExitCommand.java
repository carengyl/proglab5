package commands;

import commandLine.CommandReader;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "Shut down the program (everything not saved will be lost)");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            System.out.println("Shutting down...");
            CommandReader.togglePerformanceStatus();
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
