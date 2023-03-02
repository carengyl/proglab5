package commands;

import exceptions.InvalidNumberOfArgs;
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
            System.out.println("Список доступных команд:");
            for (AbstractCommand command: AVAILABLE_COMMANDS.values()) {
                System.out.println(command);
            }
        } catch (InvalidNumberOfArgs e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
