package commands;

import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import exceptions.NoUserInputException;
import exceptions.ValidationException;
import util.HumanBeingFactory;
import util.OutputUtil;
import util.Validators;

import java.util.ArrayList;

public class UpdateCommand extends AbstractCommand implements ComplexCommand {
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
                    "There is no Human Being with id: " + commandArgs[0],
                    Long::parseLong,
                    commandArgs[0]);
            HumanBeingFactory creator = new HumanBeingFactory(id);
            creator.setVariables();
            collection.updateById(id, creator.getCreatedHumanBeing());
            OutputUtil.printSuccessfulMessage("Updated human being by id:" + commandArgs[0]);
        } catch (InvalidNumberOfArgsException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (NoUserInputException e) {
            CommandReader.toggleProgram();
        }
    }

    @Override
    public void executeComplexCommand(String[] commandArgs, ArrayList<String> complexData) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            long id = Validators.validateArg(arg -> (collection.checkForId((long) arg)),
                    "There is no human being with id: " + commandArgs[0],
                    Long::parseLong,
                    commandArgs[0]);
            HumanBeingFactory creator = new HumanBeingFactory(id);
            creator.setVariables(complexData);
            collection.updateById(id, creator.getCreatedHumanBeing());
            OutputUtil.printSuccessfulMessage("Updated human being by id:" + commandArgs[0]);
        } catch (ValidationException | InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
