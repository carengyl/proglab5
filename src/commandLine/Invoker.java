package commandLine;

import commands.AbstractCommand;

import commands.ComplexCommand;
import exceptions.NoUserInputException;
import util.CommandHistory;
import util.OutputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class that invokes commands and executes them
 */
public class Invoker {
    /**
     * Hashmap that contains available commands and their string representation
     */
    private static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();

    private static final HashMap<String, ComplexCommand> COMPLEX_COMMANDS = new HashMap<>();
    /**
     * Command history field
     */
    private static final CommandHistory commandHistory = new CommandHistory();

    /**
     * Constructs dummy-instance
     */
    public Invoker(){}

    /**
     * Adds commands to hashmap
     *
     * @param command command
     */
    public void addCommand(AbstractCommand command) {
        AVAILABLE_COMMANDS.put(command.getName(), command);
    }

    public void addComplexCommand(AbstractCommand command) {
        AVAILABLE_COMMANDS.put(command.getName(), command);
        COMPLEX_COMMANDS.put(command.getName(), (ComplexCommand) command);
    }

    /**
     * @return command history
     */
    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    /**
     * @return available commands
     */
    public HashMap<String, AbstractCommand> getAvailableCommands() {
        return AVAILABLE_COMMANDS;
    }

    /**
     * Parses args and executes command
     *
     * @param command string command and args
     */
    public void performCommand(String command, boolean scriptUsage, ArrayList<String> complexData) throws NoUserInputException {
        String[] splitString = command.replaceAll("\s{2,}", " ").strip().split(" ");
        String commandName = splitString[0];
        String[] args = Arrays.copyOfRange(splitString, 1, splitString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executableCommand = AVAILABLE_COMMANDS.get(commandName);

            if (scriptUsage && COMPLEX_COMMANDS.containsKey(executableCommand.getName())) {
                COMPLEX_COMMANDS.get(executableCommand.getName()).executeComplexCommand(args, complexData);
            } else {
                executableCommand.executeCommand(args);

            }
            commandHistory.addToHistory(commandName);
        } else {
            if (!scriptUsage) {
                OutputUtil.printErrorMessage(commandName + ": command not found, type \"help\" to see available commands");
            }
        }
    }
}
