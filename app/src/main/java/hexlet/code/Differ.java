package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        var path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        var content1 = Files.readString(path1);
        var content2 = Files.readString(path2);

        var mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(content1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> map2 = mapper.readValue(content2, new TypeReference<Map<String, Object>>() { });

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
}
