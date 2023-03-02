import commandLine.CommandReader;

public class Main {
    public static void main(String[] args) {
        CommandReader commandReader = new CommandReader();
        commandReader.readCommandsFromConsole();
    }
}