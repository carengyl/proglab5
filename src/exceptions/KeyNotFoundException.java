package exceptions;

public class KeyNotFoundException extends Exception {
    public KeyNotFoundException() {
        super("Дракон с таким ключом не найден");
    }
}
