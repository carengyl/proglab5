package commandLine;

import commands.AbstractCommand;

import util.CommandHistory;
import util.OutputUtil;

import java.util.Arrays;
import java.util.HashMap;

public class Invoker {
    private static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();
    private static final CommandHistory commandHistory = new CommandHistory();

    public Invoker(){}

    public void addCommand(AbstractCommand command) {
        AVAILABLE_COMMANDS.put(command.getName(), command);
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public HashMap<String, AbstractCommand> getAvailableCommands() {
        return AVAILABLE_COMMANDS;
    }

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
