package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;

public class ScriptReader {

    private ArrayDeque<String> commandsFromScript = new ArrayDeque<>();

    public void readCommandsFromFile(String fileName) throws IOException{
        try (BufferedInputStream input = new BufferedInputStream(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)))) {
            final StreamUtil streamUtil = new StreamUtil();
            commandsFromScript = streamUtil.streamToArrayOfCommands(input);
        }
    }

    public ArrayDeque<String> getCommandsFromScript() {
        return commandsFromScript;
    }
}
