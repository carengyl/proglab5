package util;

import java.util.ArrayDeque;

public class CommandHistory {
    private final int historyLength = 9;
    private final ArrayDeque<String> history = new ArrayDeque<>(historyLength);

    public ArrayDeque<String> getHistory() {
        return history;
    }

    private void checkOverflow() {
        if (history.size() > historyLength) {
            history.removeFirst();
        }
    }

    public void addToHistory(String commandName) {
        this.history.addLast(commandName);
        this.checkOverflow();
    }
}
