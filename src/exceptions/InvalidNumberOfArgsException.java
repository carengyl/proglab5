package exceptions;

public class InvalidNumberOfArgsException extends Exception {
    public InvalidNumberOfArgsException(int correctNumberOfArgs, int gotNumberOfArgs) {
        super("Ожидалось аргументов: " + correctNumberOfArgs + ", получено: " + gotNumberOfArgs);
    }
}
