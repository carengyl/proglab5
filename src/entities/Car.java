package entities;

public class Car {
    private final boolean cool;
    private final String name;
    private final int cost;
    private final int horsePowers;
    private final CarBrand carBrand;

    public Car(String name, int cost, int horsePowers, CarBrand carBrand) {
        this.name = name;
        this.cost = cost;
        this.horsePowers = horsePowers;
        this.carBrand = carBrand;

        cool = (cost >= 10000000) | (horsePowers > 440) | carBrand.isCool();
    }

    public boolean isCool() {
        return cool;
    }

    @Override
    public String toString() {
        return ((cool) ? "Крутая ": "Такая себе") + "машина: "
                + "Название: " + name
                + "; Стоимость: " + cost
                + "; Мощность: " + horsePowers + "л.с."
                + "; марка: " + carBrand;
    }
}
