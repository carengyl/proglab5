package commandLine;

import commands.ExitCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import util.CommandHistory;
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
        manager = new CommandManager(
                new ExitCommand(),
                new HelpCommand(CommandManager.AVAILABLE_COMMANDS),
                new HistoryCommand(CommandManager.commandHistory));
    }

    public void readCommandsFromConsole() {
        Scanner sc = new Scanner(System.in);
        while (performanceStatus) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("Введите команду: ");
                String line = sc.nextLine().replaceAll("[\\s]{2,}", " ").strip();
                manager.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Введен недопустимый символ");
                System.exit(0);
            }
        }
    }
}
