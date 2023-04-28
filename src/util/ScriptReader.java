package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Objects;

/**
 * Class responsible for reading Commands from script
 */
public class ScriptReader {

    /**
     * Field @code ArrayDeque} of String Commands read from script
     */
    private ArrayDeque<String> commandsFromScript = new ArrayDeque<>();

    /**
     * Reads Commands from script and parses them to String commands
     *
     * @param fileName String filename
     * @throws IOException if no access or file not found
     */
    public void readCommandsFromFile(Path fileName) throws IOException{
        try (BufferedInputStream input = new BufferedInputStream(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(String.valueOf(fileName))))) {
            final StreamUtil streamUtil = new StreamUtil();
            commandsFromScript = streamUtil.streamToArrayOfCommands(input);
        }
    }

    public ArrayDeque<String> getCommandsFromScript() {
        return commandsFromScript;
    }
}
