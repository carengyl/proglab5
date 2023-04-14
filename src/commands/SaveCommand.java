package commands;

import entities.CollectionOfHumanBeings;
import util.OutputUtil;
import util.Validators;
import util.XMLParser;


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
