package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.WrongArgTypeException;
import util.OutputUtil;
import util.Validators;

public class RemoveByKeyCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public RemoveByKeyCommand(CollectionOfHumanBeings collection) {
        super("remove_key", 1, "Remove element from collection by @key", "@key - (long) unique key of element in collection");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long key = Validators.validateArg(arg -> (collection.getHumanBeings().containsKey((long) arg)),
                    "Key not found",
                    Long::parseLong,
                    commandArgs[0]);
            collection.removeByKey(key);
            OutputUtil.printSuccessfulMessage("Deleted human being by key: " + key);
        } catch (InvalidNumberOfArgsException | WrongArgTypeException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
