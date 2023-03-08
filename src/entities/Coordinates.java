package entities;

public class Coordinates {
    private int x; //Поле не может быть null
    private int y; //Значение поля должно быть больше -540, Поле не может быть null
    private static int MIN_Y = -540;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getMIN_Y() {
        return MIN_Y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
