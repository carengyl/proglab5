package entities;

public class Coordinates {
    private final int x; //Поле не может быть null
    private final int y; //Значение поля должно быть больше -540, Поле не может быть null
    private static final int MIN_Y = -540;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getMIN_Y() {
        return MIN_Y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
