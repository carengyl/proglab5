package exceptions;

public class IdNotFoundException extends Exception {
    public IdNotFoundException(){
        super("Дракон с таким ID не найден");
    }
}
