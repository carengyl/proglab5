package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.Validators;

public class RemoveLowerKey extends AbstractCommand {

    private CollectionOfHumanBeings collection;

    public RemoveLowerKey(CollectionOfHumanBeings collection) {
        super("remove_lower_key", 1, "Remove elements from collection, which key is lower than @key", "@key - (long) unique key of element in collection");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());


        } catch (InvalidNumberOfArgsException e) {
            throw new RuntimeException(e);
        }
    }
}
