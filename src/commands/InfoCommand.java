package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

public class InfoCommand extends AbstractCommand {

    private CollectionOfHumanBeings collection;

    public InfoCommand(CollectionOfHumanBeings collection) {
        super("info", "Show info about collection");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            OutputUtil.printSuccessfulMessage("Collection from file: " + collection.getFileName() + "; initialized:" + collection.getInitDate() + "; number of elements: " + collection.getHumanBeings().size());
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
