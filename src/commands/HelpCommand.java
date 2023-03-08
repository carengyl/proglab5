package commands;

import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand{
    private final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS;

    public HelpCommand(HashMap<String, AbstractCommand> AVAILABLE_COMMANDS) {
        super("help", "выводит список доступных команд");
        this.AVAILABLE_COMMANDS = AVAILABLE_COMMANDS;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.ValidateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            OutputUtil.printSuccessfulMessage("Список доступных команд:");
            for (AbstractCommand command: AVAILABLE_COMMANDS.values()) {
                OutputUtil.printSuccessfulMessage(command);
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
