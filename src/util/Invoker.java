package util;

import commands.AbstractCommand;
import commands.ClearCommand;

import java.util.Arrays;
import java.util.HashMap;

public class Invoker {
    public static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();
    public static CommandHistory commandHistory = new CommandHistory();

    public Invoker(AbstractCommand exitCommand,
                   AbstractCommand helpCommand,
                   AbstractCommand historyCommand,
                   AbstractCommand showCommand,
                   AbstractCommand insertCommand,
                   AbstractCommand updateCommand,
                   AbstractCommand removeByKeyCommand,
                   AbstractCommand clearCommand) {
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(insertCommand.getName(), insertCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeByKeyCommand.getName(), removeByKeyCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
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
