package commandLine;

import commands.*;
import entities.CollectionOfHumanBeings;
import util.OutputUtil;
import util.ScriptReader;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Class, responsible for reading commands from console and executing them
 */
public class CommandReader {
    /**
     * Field responsible for
     */
    private static boolean working = true;

    public static void toggleProgram() {
        working = !working;
    }

    private static final Invoker invoker = new Invoker();

    public CommandReader(CollectionOfHumanBeings collectionOfHumanBeings) {
        ScriptReader scriptReader = new ScriptReader();
        invoker.addCommand(new ClearCommand(collectionOfHumanBeings));
        invoker.addCommand(new ExitCommand());
        invoker.addCommand(new HelpCommand(invoker.getAvailableCommands()));
        invoker.addCommand(new HistoryCommand(invoker.getCommandHistory()));
        invoker.addCommand(new InsertCommand(collectionOfHumanBeings));
        invoker.addCommand(new RemoveKeyCommand(collectionOfHumanBeings));
        invoker.addCommand(new ShowCommand(collectionOfHumanBeings));
        invoker.addCommand(new UpdateCommand(collectionOfHumanBeings));
        invoker.addCommand(new InfoCommand(collectionOfHumanBeings));
        invoker.addCommand(new FilterByCarCommand(collectionOfHumanBeings));
        invoker.addCommand(new CountGreaterThanMoodCommand(collectionOfHumanBeings));
        invoker.addCommand(new RemoveLowerKeyCommand(collectionOfHumanBeings));
        invoker.addCommand(new SaveCommand(collectionOfHumanBeings));
        invoker.addCommand(new ExecuteScriptCommand(scriptReader));
    }

    public void readCommandsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (working) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("> ");
                String line = scanner.nextLine().replaceAll("\s{2,}", " ").strip();
                invoker.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Unexpected error");
            }
        }
    }

    public static Invoker getInvoker() {
        return invoker;
    }
}
