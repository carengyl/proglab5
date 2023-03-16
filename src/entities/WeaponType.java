package entities;

public enum WeaponType {
    AXE(),
    PISTOL(),
    SHOTGUN(),
    RIFLE(),
    MACHINE_GUN();


    // TODO: 16.03.2023 fix show to be with ordinal
    public static String show() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (WeaponType weaponType: WeaponType.values()) {
            counter++;
            sb.append(counter).append(". ").append(weaponType.toString()).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
