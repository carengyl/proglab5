package exceptions;

public class IdNotFoundException extends Exception {
    public IdNotFoundException(){
        super("Человек с таким ID не найден");
    }
}
