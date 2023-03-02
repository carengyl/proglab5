package exceptions;

public class KeyNotFoundException extends Exception {
    public KeyNotFoundException() {
        super("Человек с таким ключом не найден");
    }
}
