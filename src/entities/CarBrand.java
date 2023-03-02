package entities;

public enum CarBrand {
    COOL_BRAND("Крутая марка", true),
    NOT_COOL_BRAND("Не очень крутая марка", false);

    private final String name;
    private final boolean cool;

    CarBrand(String name, boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    public boolean isCool() {
        return cool;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (CarBrand carBrand: CarBrand.values()) {
            k++;
            sb.append(k).append(". ").append(carBrand.name).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
