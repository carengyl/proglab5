package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.Validators;

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
            collection.getHumanBeings().clear();
        } catch (InvalidNumberOfArgsException e) {
            throw new RuntimeException(e);
        }
    }
}
