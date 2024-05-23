package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;

import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = Parser.parse(
            readFile(filepath1),
            FilenameUtils.getExtension(filepath1)
        );

        var map2 = Parser.parse(
            readFile(filepath2),
            FilenameUtils.getExtension(filepath2)
        );

        var keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        var result = new ArrayList<String>();
        result.add("{");

        for (var key : keys) {
            var value1 = map1.get(key);
            var value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                result.add("  + " + key + ": " + value2);
            } else if (!map2.containsKey(key)) {
                result.add("  - " + key + ": " + value1);
            } else if (value1.equals(value2)) {
                result.add("    " + key + ": " + value1);
            } else {
                result.add("  - " + key + ": " + value1);
                result.add("  + " + key + ": " + value2);
            }
        }

        result.add("}");

        return String.join("\n", result);
    }

    private static String readFile(String filepath) throws IOException {
        var path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
