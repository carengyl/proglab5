package entities;

public enum Mood {
    LONGING(),
    CALM(),
    RAGE(),
    FRENZY();

    public static Mood getMoodByNumber(int i) {
        for (Mood mood: Mood.values()) {
            if (mood.ordinal() == i-1) {
                return mood;
            }
        }
        return null;
    }

    public static String show() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (Mood mood: Mood.values()) {
            k++;
            sb.append(k).append(". ").append(mood.toString()).append('\n');
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }
}
