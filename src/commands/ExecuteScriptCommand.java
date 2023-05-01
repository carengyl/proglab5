package commands;

import commandLine.CommandReader;
import entities.HumanBeing;
import exceptions.CommandExecutionException;
import exceptions.InvalidNumberOfArgsException;
import exceptions.NoUserInputException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.ScriptReader;
import util.Validators;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ExecuteScriptCommand extends AbstractCommand {

    private final ScriptReader scriptReader;

    private static final Set<Path> fileHistory = new HashSet<>();
    public ExecuteScriptCommand(ScriptReader scriptReader) {
        super("execute_script", 1, "Execute script from @file", "@file - path to file with script");

        this.scriptReader = scriptReader;
    }

    @Override
    public void executeCommand(String[] commandArgs) throws NoUserInputException {
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
                ArrayList<String> commands = scriptReader.getCommandsFromScript();
                if (commands.contains("execute_script " + commandArgs[0])) {
                    throw new CommandExecutionException("Script calls itself from inside. Loop possible");
                }
                for (int i=0; i < commands.size(); i++) {
                    String command = commands.get(i);
                    OutputUtil.printSuccessfulMessage(command);
                    if (i <= commands.size()-HumanBeing.getNumberOfFields()-2) {
                        CommandReader.getInvoker().performCommand(command,
                                true,
                                new ArrayList<>(commands.subList(i+1, i + 2 + HumanBeing.getNumberOfFields())));
                    } else {
                        CommandReader.getInvoker().performCommand(command,
                                true,
                                new ArrayList<>(commands.subList(i+1, commands.size())));
                    }
                }
                fileHistory.remove(fileName);
            }
        } catch (InvalidNumberOfArgsException | IOException | CommandExecutionException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
