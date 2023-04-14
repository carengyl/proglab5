package commands;

import commandLine.CommandReader;
import exceptions.CommandExecutionException;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.ScriptReader;
import util.Validators;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand {

    private final ScriptReader scriptReader;

    private static final Set<String> fileHistory = new HashSet<>();
    public ExecuteScriptCommand(ScriptReader scriptReader) {
        super("execute_script", 1, "Execute script from @file_name.txt", "@file_name - txt file_name with script");

        this.scriptReader = scriptReader;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());

            if (fileHistory.contains(commandArgs[0])) {
                OutputUtil.printErrorMessage("Loop possible");
            } else {
                fileHistory.add(commandArgs[0]);
                scriptReader.readCommandsFromFile(commandArgs[0]);
                ArrayDeque<String> commands = scriptReader.getCommandsFromScript();
                if (commands.contains("execute_script " + commandArgs[0])) {
                    throw new CommandExecutionException("Script calls itself from inside. Loop possible");
                }
                for (String command : commands) {
                    OutputUtil.printSuccessfulMessage(command);
                    CommandReader.getInvoker().performCommand(command);
                }
                fileHistory.remove(commandArgs[0]);
            }
        } catch (InvalidNumberOfArgsException | IOException | CommandExecutionException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
