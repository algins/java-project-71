package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FilenameUtils;

import hexlet.code.formatters.Formatter;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Formatter.FORMAT_STYLISH);
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = Parser.parse(
            readFile(filepath1),
            FilenameUtils.getExtension(filepath1)
        );

        var map2 = Parser.parse(
            readFile(filepath2),
            FilenameUtils.getExtension(filepath2)
        );

        var diffTree = createDiffTree(map1, map2);
        return Formatter.format(diffTree, format);
    }

    private static DiffTree createDiffTree(Map<String, Object> map1, Map<String, Object> map2) {
        var diffTree = new DiffTree();
        var keys = getUniqueKeys(map1, map2);

        for (var key : keys) {
            var diffNode = createDiffNode(key, map1, map2);
            diffTree.addNode(diffNode);
        }

        return diffTree;
    }

    private static DiffNode createDiffNode(String key, Map<String, Object> map1, Map<String, Object> map2) {
        var value1 = map1.get(key);
        var value2 = map2.get(key);

        if (!map1.containsKey(key)) {
            return new DiffNode(DiffNode.TYPE_ADDED, key, value2);
        }

        if (!map2.containsKey(key)) {
            return new DiffNode(DiffNode.TYPE_REMOVED, key, value1);
        }

        if (Objects.equals(value1, value2)) {
            return new DiffNode(DiffNode.TYPE_UNCHANGED, key, value1);
        }

        return new DiffNode(DiffNode.TYPE_CHANGED, key, value1, value2);
    }

    private static Set<String> getUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {
        var keys = new TreeSet<String>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }

    private static String readFile(String filepath) throws IOException {
        var path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
