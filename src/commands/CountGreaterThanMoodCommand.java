package commands;

import entities.CollectionOfHumanBeings;
import entities.Mood;
import exceptions.InvalidNumberOfArgsException;
import exceptions.ValidationException;
import util.OutputUtil;
import util.Validators;

import java.util.Objects;

public class CountGreaterThanMoodCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public CountGreaterThanMoodCommand(CollectionOfHumanBeings collection) {
        super("count_greater_than_mood", 1, "Count collection elements which mood is greater than @mood", "@mood from Mood enum");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            int moodNumber = Validators.validateArg(arg -> ((int) arg < Mood.values().length + 1) && ((int) arg > 0),
                    "Pick a Mood number:\n" + Mood.show(),
                    Integer::parseInt,
                    commandArgs[0]);
            Mood mood = Mood.getMoodByNumber(moodNumber);
            int greaterMoods = 0;
            for (long key: collection.getHumanBeings().keySet()) {
                if (Objects.requireNonNull(mood).compareTo(collection.getHumanBeings().get(key).getMood()) < 0) {
                    greaterMoods++;
                }
            }
            OutputUtil.printSuccessfulMessage("People with Mood greater than " + mood + ":" + greaterMoods);
        } catch (InvalidNumberOfArgsException | ValidationException e) {
            OutputUtil.printSuccessfulMessage(e.getMessage());
        }
    }
}
