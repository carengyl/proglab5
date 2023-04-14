package commandLine;

import commands.AbstractCommand;

import util.CommandHistory;
import util.OutputUtil;

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
     * @param command
     */
    public void performCommand(String command){
        String[] splitString = command.split(" ");
        String commandName = splitString[0];
        String[] args = Arrays.copyOfRange(splitString, 1, splitString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executableCommand = AVAILABLE_COMMANDS.get(commandName);
            executableCommand.executeCommand(args);
            commandHistory.addToHistory(commandName);
        } else {
            OutputUtil.printErrorMessage(commandName + ": command not found, type \"help\" to see available commands");
        }
    }
}
