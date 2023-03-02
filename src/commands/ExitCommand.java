package commands;

import commandLine.CommandReader;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "завершить работу с коллекцией (все несохраненные изменения будут потеряны)");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        System.out.println("Завершение работы.");
        CommandReader.togglePerformanceStatus();
    }
}
