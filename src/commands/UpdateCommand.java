package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.WrongArgTypeException;
import util.HumanBeingCreator;
import util.OutputUtil;
import util.Validators;

public class UpdateCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public UpdateCommand(CollectionOfHumanBeings collection) {
        super("update", 1, "Update element by @id", "@id - (long) id of collection element");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long id = Validators.validateArg(arg -> (collection.checkForId((long) arg)),
                    "Key isn't unique",
                    Long::parseLong,
                    commandArgs[0]);
            HumanBeingCreator creator = new HumanBeingCreator(id);
            creator.setVariables();
            collection.updateById(id, creator.getCreatedHumanBeing());
        } catch (InvalidNumberOfArgsException | WrongArgTypeException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}