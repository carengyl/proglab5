package commands;

import exceptions.InvalidNumberOfArgs;
import util.CommandHistory;
import util.OutputUtil;
import util.Validators;

public class HistoryCommand extends AbstractCommand {
    private CommandHistory historyCommand;

    public HistoryCommand(CommandHistory historyCommand){
        super("history", "выводит последние 9 выполненных команд");
        this.historyCommand = historyCommand;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.ValidateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            for (String command: historyCommand.getHistory()) {
                OutputUtil.printSuccessfulMessage(command);
            }
        } catch (InvalidNumberOfArgs e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
