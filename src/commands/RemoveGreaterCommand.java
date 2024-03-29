package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.NoUserInputException;
import util.HumanBeingFactory;
import util.OutputUtil;
import util.Validators;

public class RemoveGreaterCommand extends AbstractCommand{

    private final CollectionOfHumanBeings collection;

    public RemoveGreaterCommand(CollectionOfHumanBeings collection) {
        super("remove_greater", "Remove all elements, which are greater than {element}");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) throws NoUserInputException {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            HumanBeingFactory creator = new HumanBeingFactory();
            creator.setVariables();
            for (long key: collection.getHumanBeings().keySet()) {
                if (collection.getHumanBeings().get(key).compareTo(creator.getCreatedHumanBeing()) > 0) {
                   collection.removeByKey(key);
                }
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
