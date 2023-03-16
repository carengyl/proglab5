package exceptions;

public class CollectionEmptyException extends Exception{
    public CollectionEmptyException() {
        super("Collection is empty");
    }
}
