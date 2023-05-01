import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;
import util.OutputUtil;
import util.XMLParser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Main class
 */
public class Client {
    public static void main(String[] args) {
        CollectionOfHumanBeings collection;
        try {
            XMLParser parser = new XMLParser();
            Path filename = Path.of(System.getenv("XML_FILE"));
            collection = parser.readFromXML(filename);
            if (collection == null) {
                OutputUtil.printSuccessfulMessage("Creating new Collection (it will be saved in: " +  filename
                        + "). Waiting for commands...");
                collection = new CollectionOfHumanBeings(filename);
            }
        } catch (IOException | NullPointerException e) {
            OutputUtil.printErrorMessage("Unable to get to the file.");
            OutputUtil.printSuccessfulMessage("Creating new Collection, without file. Waiting for commands...");
            collection = new CollectionOfHumanBeings(null);
        }
        CommandReader commandReader = new CommandReader(collection);
        commandReader.readCommandsFromConsole();
    }
}