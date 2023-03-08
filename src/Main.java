import commandLine.CommandReader;
import entities.CollectionOfHumanBeings;

public class Main {
    public static void main(String[] args) {
        CollectionOfHumanBeings collection = new CollectionOfHumanBeings("someFile.txt");
        CommandReader commandReader = new CommandReader(collection);
        commandReader.readCommandsFromConsole();
    }
}