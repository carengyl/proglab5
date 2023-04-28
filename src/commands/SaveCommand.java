package commands;

import entities.CollectionOfHumanBeings;
import exceptions.InvalidNumberOfArgsException;
import util.OutputUtil;
import util.Validators;
import util.XMLParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;


public class SaveCommand extends AbstractCommand {
    private final CollectionOfHumanBeings collection;

    public SaveCommand(CollectionOfHumanBeings collection) {
        super("save", "Save current collection to XML file");
        this.collection = collection;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateNumberOfArgs(commandArgs, this.getNumberOfArgs());
            XMLParser xmlParser = new XMLParser();
            xmlParser.writeToXML(collection);
            OutputUtil.printSuccessfulMessage("Collection saved to file " + collection.getFileName());
        } catch (InvalidNumberOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (IOException e) {
            OutputUtil.printErrorMessage("Unable to get to the file.");
            Scanner scanner = new Scanner(System.in);
            String newFile = Validators.validateStringInput("Please enter path to backup file", true, scanner);
            if (!Objects.equals(newFile, "")) {
                collection.setFileName(Path.of(newFile));
                this.executeCommand(commandArgs);
            }
        }
    }
}
