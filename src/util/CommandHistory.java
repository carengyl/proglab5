package util;

import java.util.ArrayDeque;

/**
 * Class responsible for Commands History
 */
public class CommandHistory {
    /**
     * history length field
     */
    private final int historyLength = 9;
    /**
     * {@code ArrayDeque} of String Commands
     */
    private final ArrayDeque<String> history = new ArrayDeque<>(historyLength);

    /**
     * @return {@code ArrayDeque} of String Commands
     */
    public ArrayDeque<String> getHistory() {
        return history;
    }

    private void checkOverflow() {
        if (history.size() > historyLength) {
            history.removeFirst();
        }
    }

    /**
     * Adds Command to history
     *
     * @param commandName Command
     */
    public void addToHistory(String commandName) {
        this.history.addLast(commandName);
        this.checkOverflow();
    }
}
