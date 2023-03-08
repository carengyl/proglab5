package commands;

import commandLine.CommandReader;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "завершает работу с коллекцией (все несохраненные изменения будут потеряны)");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.ValidateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            System.out.println("Завершение работы.");
            CommandReader.togglePerformanceStatus();
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
