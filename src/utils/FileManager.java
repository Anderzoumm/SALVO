package utils;

import java.io.*;

public class FileManager {

    public static void saveData(
            FileOutputStream file,
            String content
    ) throws IOException {

        OutputStreamWriter writer =
                new OutputStreamWriter(file);

        BufferedWriter buffer =
                new BufferedWriter(writer);

        buffer.write(content);

        buffer.close();
    }

    public static String loadData(
            FileInputStream file
    ) throws IOException {

        InputStreamReader reader =
                new InputStreamReader(file);

        BufferedReader buffer =
                new BufferedReader(reader);

        StringBuilder content =
                new StringBuilder();

        while (true) {

            String line = buffer.readLine();

            if (line == null) {
                break;
            }

            content.append(line);
            content.append("\n");
        }

        buffer.close();

        return content.toString();
    }
}