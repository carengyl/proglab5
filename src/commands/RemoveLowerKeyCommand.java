package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.Validators;

public class RemoveLowerKeyCommand extends AbstractCommand {

    private final CollectionOfHumanBeings collection;

    // TODO: 13.04.2023 class argument
    public RemoveLowerKeyCommand(CollectionOfHumanBeings collection) {
        super("remove_lower_key", 1, "Remove elements from collection, which key is lower than @key", "@key - (long) unique key of element in collection");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long greaterKey = Validators.validateArg(arg -> true,
                    "Expected long type",
                    Long::parseLong,
                    commandArgs[0]);
            collection.removeLowerKey(greaterKey);
        } catch (InvalidNumberOfArgsException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
