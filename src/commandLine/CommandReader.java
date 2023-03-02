package commandLine;

import commands.ExitCommand;
import util.CommandManager;
import util.OutputUtil;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandReader {
    private static boolean performanceStatus = true;

    public static void togglePerformanceStatus() {
        performanceStatus = !performanceStatus;
    }

    private CommandManager manager;

    public CommandReader() {
        manager = new CommandManager(new ExitCommand());
    }

    public void readCommandsFromConsole() {
        Scanner sc = new Scanner(System.in);
        while (performanceStatus) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("Введите команду: ");
                String line = sc.nextLine();
                manager.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Введен недопустимый символ");
                System.exit(0);
            }
        }
    }
}
