import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;
import util.OutputUtil;
import util.XMLParser;

import java.io.IOException;
import java.nio.file.Path;

public class Client {
    public static void main(String[] args) {
        try {
            XMLParser parser = new XMLParser();
            CollectionOfHumanBeings collection = parser.readFromXML(Path.of(System.getenv("XML_FILE")));
            CommandReader commandReader = new CommandReader(collection);
            commandReader.readCommandsFromConsole();
        } catch (IOException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}