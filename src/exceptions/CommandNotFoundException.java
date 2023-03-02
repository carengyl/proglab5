package exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException() {
        super("Команда не найдена, для справки введите команду help");
    }
}
