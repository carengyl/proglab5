package commands;

import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand{
    private final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS;

    public HelpCommand(HashMap<String, AbstractCommand> AVAILABLE_COMMANDS) {
        super("help", "Display information about builtin commands");
        this.AVAILABLE_COMMANDS = AVAILABLE_COMMANDS;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            OutputUtil.printSuccessfulMessage("Builtin commands:");
            for (AbstractCommand command: AVAILABLE_COMMANDS.values()) {
                OutputUtil.printSuccessfulMessage(command);
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
