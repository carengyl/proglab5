package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

import java.util.Scanner;

public class ClearCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public ClearCommand(CollectionOfHumanBeings collection) {
        super("clear", "Clear collection");
        this.collection = collection;
    }
    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            boolean userInput = Validators.validateBooleanInput("You're going to delete all collection. Are you sure",
                    new Scanner(System.in));
            if (userInput) {
                collection.getHumanBeings().clear();
                OutputUtil.printSuccessfulMessage("Collection has been cleared.");
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
