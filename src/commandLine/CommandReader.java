package commandLine;

import commands.*;
import entities.CollectionOfHumanBeings;
import util.Invoker;
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
        invoker = new Invoker(
                new ExitCommand(),
                new HelpCommand(Invoker.AVAILABLE_COMMANDS),
                new HistoryCommand(Invoker.commandHistory),
                new ShowCommand(collectionOfHumanBeings),
                new InsertCommand(collectionOfHumanBeings),
                new UpdateCommand(collectionOfHumanBeings),
                new RemoveByKeyCommand(collectionOfHumanBeings),
                new ClearCommand(collectionOfHumanBeings));
    }

    public void readCommandsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (performanceStatus) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("> ");
                String line = scanner.nextLine().replaceAll("\s{2,}", " ").strip();
                invoker.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("IDK WTF");
                System.exit(0);
            }
        }
    }
}
