package entities;

public enum WeaponType {
    AXE("Топор"),
    PISTOL("Пистолет"),
    SHOTGUN("Дробовик"),
    RIFLE("Винтовка"),
    MACHINE_GUN("Пулемёт");

    private final String name;
    WeaponType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (WeaponType weaponType: WeaponType.values()) {
            k++;
            sb.append(k).append(". ").append(weaponType.name).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
