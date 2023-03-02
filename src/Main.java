import commandLine.CommandReader;
import entities.Car;
import entities.CarBrand;
import entities.Mood;

public class Main {
    public static void main(String[] args) {
        CommandReader commandReader = new CommandReader();
        commandReader.readCommandsFromConsole();
    }
}