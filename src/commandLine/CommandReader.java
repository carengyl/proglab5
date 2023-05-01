package commandLine;

import commands.*;
import entities.CollectionOfHumanBeings;
import exceptions.NoUserInputException;
import util.OutputUtil;
import util.ScriptReader;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Class, responsible for reading commands from console and executing them
 */
public class CommandReader {
    /**
     * Field responsible for command working
     */
    private static boolean working = true;

    /**
     * Toggles program working status
     */
    public static void toggleProgram() {
        working = !working;
    }

    /**
     * Invoker field
     */
    private static final Invoker invoker = new Invoker();

    /**
     * Constructs new instance with given collection. Adds command to invoker
     *
     * @param collectionOfHumanBeings parsed collection
     */
    public CommandReader(CollectionOfHumanBeings collectionOfHumanBeings) {
        ScriptReader scriptReader = new ScriptReader();
        invoker.addCommand(new ClearCommand(collectionOfHumanBeings));
        invoker.addCommand(new ExitCommand());
        invoker.addCommand(new HelpCommand(invoker.getAvailableCommands()));
        invoker.addCommand(new HistoryCommand(invoker.getCommandHistory()));

        invoker.addComplexCommand(new InsertCommand(collectionOfHumanBeings));
        invoker.addComplexCommand(new UpdateCommand(collectionOfHumanBeings));

        invoker.addCommand(new RemoveKeyCommand(collectionOfHumanBeings));
        invoker.addCommand(new ShowCommand(collectionOfHumanBeings));


        invoker.addCommand(new InfoCommand(collectionOfHumanBeings));
        invoker.addCommand(new FilterByCarCommand(collectionOfHumanBeings));
        invoker.addCommand(new CountGreaterThanMoodCommand(collectionOfHumanBeings));
        invoker.addCommand(new RemoveLowerKeyCommand(collectionOfHumanBeings));

        invoker.addCommand(new SaveCommand(collectionOfHumanBeings));
        invoker.addCommand(new ExecuteScriptCommand(scriptReader));
        invoker.addCommand(new CountLessThanMinutesOfWaitingCommand(collectionOfHumanBeings));
        invoker.addCommand(new RemoveGreaterCommand(collectionOfHumanBeings));
    }

    /**
     * Reads commands from console and passes command to invoker
     */
    public void readCommandsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (working) {
            OutputUtil.printSuccessfulMessageOneStrip("> ");
            try {
                String line = scanner.nextLine().replaceAll("\s{2,}", " ").strip();
                invoker.performCommand(line, false, null);
            } catch (NoSuchElementException | NoUserInputException e) {
                OutputUtil.printErrorMessage(e.getMessage());
                working = false;
            }
        }
    }

    /**
     * @return invoker
     */
    public static Invoker getInvoker() {
        return invoker;
    }
}
