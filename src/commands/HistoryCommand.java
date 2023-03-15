package commands;

import exceptions.InvalidNumberOfArgsException;
import util.CommandHistory;
import util.OutputUtil;
import util.Validators;

public class HistoryCommand extends AbstractCommand {
    private final CommandHistory historyCommand;

    public HistoryCommand(CommandHistory historyCommand){
        super("history", "Show last 9 executed commands");
        this.historyCommand = historyCommand;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            for (String command: historyCommand.getHistory()) {
                OutputUtil.printSuccessfulMessage(command);
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
