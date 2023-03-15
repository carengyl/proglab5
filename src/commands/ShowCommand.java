package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

public class ShowCommand extends AbstractCommand{
    private final CollectionOfHumanBeings collection;
    public ShowCommand(CollectionOfHumanBeings collection) {
        super("show", "Show data about collection and elements");
        this.collection = collection;
    }
    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            OutputUtil.printSuccessfulMessage("Collection from file: " + collection.getFileName());
            if (!collection.getHumanBeings().isEmpty()) {
                for (long key : collection.getHumanBeings().keySet()) {
                    OutputUtil.printSuccessfulMessageOneStrip("Key: " + key + "; ");
                    OutputUtil.printSuccessfulMessage(collection.getHumanBeings().get(key));
                }
            } else {
                OutputUtil.printErrorMessage("Collection is empty");
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
