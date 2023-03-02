package util;

import commands.AbstractCommand;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager {
    public static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();
    public static CommandHistory commandHistory = new CommandHistory();

    public CommandManager(AbstractCommand exitCommand, AbstractCommand helpCommand, AbstractCommand historyCommand) {
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
    }

    public void performCommand(String command){
        String[] splitString = command.split(" ");
        String commandName = splitString[0].toLowerCase();
        String[] args = Arrays.copyOfRange(splitString, 1, splitString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executableCommand = AVAILABLE_COMMANDS.get(commandName);
            executableCommand.executeCommand(args);
            commandHistory.addToHistory(commandName);
        } else {
            OutputUtil.printErrorMessage("Команда не найдена, для справки введите help");
        }
    }
}
