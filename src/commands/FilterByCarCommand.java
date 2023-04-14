package commands;

import entities.CollectionOfHumanBeings;
import exceptions.CommandExecutionException;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.Validators;

import java.util.Objects;

public class FilterByCarCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public FilterByCarCommand(CollectionOfHumanBeings collection) {
        super("filter_by_car", 1, "Show collection elements, which car equals @car", "@car - \"true\" string equals true, others to false");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            Boolean car = Validators.validateArg(arg -> true,
                    "should be true or false",
                    Boolean::parseBoolean,
                    commandArgs[0]);

            if (!collection.getHumanBeings().isEmpty()) {
                for (long key : collection.getHumanBeings().keySet()) {
                    if (collection.getHumanBeings().get(key).getCar() != null) {
                        if (Objects.equals(collection.getHumanBeings().get(key).getCar().isCool(), car)) {
                            OutputUtil.printSuccessfulMessage(collection.getHumanBeings().get(key));
                        }
                    }
                }
            } else {
                throw new CommandExecutionException("Collection is empty");
            }
        } catch (InvalidNumberOfArgsException | ValidationException | CommandExecutionException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}