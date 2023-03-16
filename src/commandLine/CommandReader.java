package commandLine;

import commands.*;
import entities.CollectionOfHumanBeings;
import util.OutputUtil;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandReader {
    private static boolean performanceStatus = true;

    public static void togglePerformanceStatus() {
        performanceStatus = !performanceStatus;
    }

    private final Invoker invoker;

    public CommandReader(CollectionOfHumanBeings collectionOfHumanBeings) {
        invoker = new Invoker();
        invoker.addCommand(new ClearCommand(collectionOfHumanBeings));
        invoker.addCommand(new ExitCommand());
        invoker.addCommand(new HelpCommand(invoker.getAvailableCommands()));
        invoker.addCommand(new HistoryCommand(invoker.getCommandHistory()));
        invoker.addCommand(new InsertCommand(collectionOfHumanBeings));
        invoker.addCommand(new RemoveByKeyCommand(collectionOfHumanBeings));
        invoker.addCommand(new ShowCommand(collectionOfHumanBeings));
        invoker.addCommand(new UpdateCommand(collectionOfHumanBeings));
        invoker.addCommand(new InfoCommand(collectionOfHumanBeings));
        invoker.addCommand(new FilterByCarCommand(collectionOfHumanBeings));
        invoker.addCommand(new CountGreaterThanMoodCommand(collectionOfHumanBeings));
    }

    public void readCommandsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (performanceStatus) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("> ");
                String line = scanner.nextLine().replaceAll("\s{2,}", " ").strip();
                invoker.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Unexpected error");
                // TODO: 16.03.2023 no system exit
            }
        }
    }
}
