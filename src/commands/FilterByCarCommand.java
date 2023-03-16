package commands;

import entities.CollectionOfHumanBeings;
import exceptions.CollectionEmptyException;
import exceptions.InvalidNumberOfArgsException;
import exceptions.WrongArgTypeException;
import util.OutputUtil;
import util.Validators;

import java.util.Objects;

public class FilterByCarCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public FilterByCarCommand(CollectionOfHumanBeings collection) {
        super("filter_by_car", 1, "Show collection elements, which car equals @car", "@car - boolean car cool variable");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            boolean car = Validators.validateArg(arg -> true,
                    "should be true or false",
                    Boolean::parseBoolean,
                    commandArgs[0]);
            if (!collection.getHumanBeings().isEmpty()) {
                for (long key : collection.getHumanBeings().keySet()) {
                    if (Objects.equals(collection.getHumanBeings().get(key).getCar().isCool(), car)) {
                        OutputUtil.printSuccessfulMessage(collection.getHumanBeings().get(key));
                    }
                }
            } else {
                throw new CollectionEmptyException();
            }
        } catch (InvalidNumberOfArgsException | WrongArgTypeException | CollectionEmptyException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
