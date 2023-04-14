package util;

import java.io.*;
import java.util.ArrayDeque;

public class StreamUtil {

    public ArrayDeque<String> streamToArrayOfCommands(BufferedInputStream input) throws IOException {
        ArrayDeque<String> commands = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line = br.readLine();
        while (line != null) {
            commands.add(line);
            line = br.readLine();
        }
        br.close();
        return commands;
    }


    public String streamToString(BufferedInputStream inputStream) throws IOException {
        byte[] contents = new byte[1024];
        int bytesRead;
        StringBuilder strFileContents = new StringBuilder();
        while((bytesRead = inputStream.read(contents)) != -1) {
            strFileContents.append(new String(contents, 0, bytesRead));
        }
        return strFileContents.toString();
    }
}
