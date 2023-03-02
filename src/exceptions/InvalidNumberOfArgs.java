package exceptions;

public class InvalidNumberOfArgs extends Exception {
    public InvalidNumberOfArgs(int correctNumberOfArgs, int gotNumberOfArgs) {
        super("Ожидалось аргументов: " + correctNumberOfArgs + ", получено: " + gotNumberOfArgs);
    }
}
