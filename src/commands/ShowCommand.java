package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;

public class ShowCommand extends AbstractCommand{
    private CollectionOfHumanBeings collection;
    public ShowCommand(CollectionOfHumanBeings collection) {
        super("show", "выводит данные об элементах коллекции");
        this.collection = collection;
    }
    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.ValidateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            OutputUtil.printSuccessfulMessage("Коллекция из файла: " + collection.getFileName());
            if (!collection.getHumanBeings().isEmpty()) {
                for (long key : collection.getHumanBeings().keySet()) {
                    OutputUtil.printSuccessfulMessage(collection.getHumanBeings().get(key));
                }
            } else {
                OutputUtil.printErrorMessage("Коллекция пуста");
            }
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
