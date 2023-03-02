package util;

import commands.AbstractCommand;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager {
    public static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();

    public CommandManager(AbstractCommand exitCommand) {
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
    }

    public void performCommand(String command){
        String[] splitString = command.split(" ");
        String commandName = splitString[0].toLowerCase();
        String[] args = Arrays.copyOfRange(splitString, 1, splitString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executableCommand = AVAILABLE_COMMANDS.get(commandName);
            executableCommand.executeCommand(args);
        } else {
            OutputUtil.printErrorMessage("Команда не найдена, для справки введите help");
        }
    }
}
