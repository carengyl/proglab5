package entities;

public enum CarBrand {
    COOL_BRAND(true),
    NOT_COOL_BRAND(false);
    private final boolean cool;

    CarBrand(boolean cool) {
        this.cool = cool;
    }

    public boolean isCool() {
        return cool;
    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (CarBrand carBrand: CarBrand.values()) {
            k++;
            sb.append(k).append(". ").append(carBrand.toString()).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
