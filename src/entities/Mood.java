package entities;

public enum Mood {
    LONGING("Жаждущее"),
    CALM("Спокойное"),
    RAGE("Яростное"),
    FRENZY("Неистовое");

    private final String name;
    Mood(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (Mood mood: Mood.values()) {
            k++;
            sb.append(k).append(". ").append(mood.name).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
