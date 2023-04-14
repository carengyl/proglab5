package commands;

import entities.CollectionOfHumanBeings;
import entities.Mood;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.Validators;

public class CountLessThanMinutesOfWaitingCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public CountLessThanMinutesOfWaitingCommand(CollectionOfHumanBeings collection) {
        super("count_less_than_minutes_of_waiting", 1,
                "Counts humanBeings, which minutes of waiting is less than @minutes_of_waiting",
                "@minutes_of_waiting (double) minutes of waiting");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            double minutesOfWaiting = Validators.validateArg(arg -> true,
                    "Pick a Mood number:\n" + Mood.show(),
                    Double::parseDouble,
                    commandArgs[0]);

            int counter = 0;
            for (long key: collection.getHumanBeings().keySet()) {
                if (collection.getHumanBeings().get(key).getMinutesOfWaiting() != null) {
                    if (collection.getHumanBeings().get(key).getMinutesOfWaiting() > minutesOfWaiting) {
                        counter++;
                    }
                }
            }
            OutputUtil.printSuccessfulMessage("People with minutes of waiting greater than " + minutesOfWaiting + ": " + counter);
        } catch (InvalidNumberOfArgsException | ValidationException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
