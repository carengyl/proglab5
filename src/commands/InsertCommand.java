package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.HumanBeingFactory;
import util.OutputUtil;
import util.Validators;

import java.util.ArrayList;

public class InsertCommand extends AbstractCommand implements ComplexCommand{
    private final CollectionOfHumanBeings collection;

    public InsertCommand(CollectionOfHumanBeings collection) {
        super("insert", 1, "Add element to collection by @key", "@key - unique long of element");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long key = Validators.validateArg(arg -> (!collection.getHumanBeings().containsKey((long) arg)),
                    "Key isn't unique",
                    Long::parseLong,
                    commandArgs[0]);
            HumanBeingFactory creator = new HumanBeingFactory();
            creator.setVariables();
            collection.addByKey(key, creator.getCreatedHumanBeing());
        } catch (InvalidNumberOfArgsException | ValidationException | IllegalArgumentException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public void executeComplexCommand(String[] commandArgs, ArrayList<String> complexData) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long key = Validators.validateArg(arg -> (!collection.getHumanBeings().containsKey((long) arg)),
                    "Key isn't unique",
                    Long::parseLong,
                    commandArgs[0]);
            HumanBeingFactory creator = new HumanBeingFactory();
            creator.setVariables(complexData);
            collection.addByKey(key, creator.getCreatedHumanBeing());
        } catch (InvalidNumberOfArgsException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
