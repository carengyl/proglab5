import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;

public class Client {
    public static void main(String[] args) {
        CollectionOfHumanBeings collection = new CollectionOfHumanBeings(System.getenv("XML_FILE"));
        CommandReader commandReader = new CommandReader(collection);
        commandReader.readCommandsFromConsole();
    }
}