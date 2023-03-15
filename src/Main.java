import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;

public class Main {
    public static void main(String[] args) {
        //collection = xml.parse("someFile.xml")
        CollectionOfHumanBeings collection = new CollectionOfHumanBeings("someFile.xml");
        CommandReader commandReader = new CommandReader(collection);
        commandReader.readCommandsFromConsole();
    }
}