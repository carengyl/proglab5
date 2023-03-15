package exceptions;

public class IdNotFoundException extends Exception {
    public IdNotFoundException(){
        super("Human with such ID is not found");
    }
}
