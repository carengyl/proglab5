package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.HumanBeingCreator;
import util.OutputUtil;
import util.Validators;

public class AddCommand extends AbstractCommand {

    private final CollectionOfHumanBeings collection;

    public AddCommand(CollectionOfHumanBeings collection) {
        super("add", "добавляет новый элемент в коллекцию");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.ValidateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            HumanBeingCreator creator = new HumanBeingCreator();
            creator.setVariables();
            // TODO: 07.03.2023 add exception if key (id) isn't unique
            collection.addHumanBeing(creator.getCreatedHumanBeing());
            OutputUtil.printSuccessfulMessage(creator.getCreatedHumanBeing());
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
