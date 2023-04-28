package commands;

import commandLine.CommandReader;
import exceptions.CommandExecutionException;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.ScriptReader;
import util.Validators;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand {

    private final ScriptReader scriptReader;

    private static final Set<Path> fileHistory = new HashSet<>();
    public ExecuteScriptCommand(ScriptReader scriptReader) {
        super("execute_script", 1, "Execute script from @file", "@file - path to file with script");

        this.scriptReader = scriptReader;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            Path fileName = Validators.validateArg(arg -> true,
                    "Enter a path to file",
                    Path::of,
                    commandArgs[0]);

            if (fileHistory.contains(fileName)) {
                OutputUtil.printErrorMessage("Loop possible");
            } else {
                fileHistory.add(fileName);
                scriptReader.readCommandsFromFile(fileName);
                ArrayDeque<String> commands = scriptReader.getCommandsFromScript();
                if (commands.contains("execute_script " + commandArgs[0])) {
                    throw new CommandExecutionException("Script calls itself from inside. Loop possible");
                }
                for (String command : commands) {
                    OutputUtil.printSuccessfulMessage(command);
                    CommandReader.getInvoker().performCommand(command);
                }
                fileHistory.remove(fileName);
            }
        } catch (InvalidNumberOfArgsException | IOException | CommandExecutionException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
