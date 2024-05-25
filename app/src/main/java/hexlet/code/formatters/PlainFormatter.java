package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hexlet.code.DiffNode;
import hexlet.code.DiffTree;

public class PlainFormatter {
    public static String format(DiffTree diffTree) throws IOException {
        return diffTree.getNodes().stream()
            .filter(node -> !node.getType().equals(DiffNode.TYPE_UNCHANGED))
            .map(PlainFormatter::formatNode)
            .collect(Collectors.joining("\n"));
    }

    private static String formatNode(DiffNode node) {
        var type = node.getType();
        var key = node.getKey();
        var value1 = node.getValue1();
        var value2 = node.getValue2();

        switch (type) {
            case DiffNode.TYPE_ADDED:
                return "Property '" + key + "' was added with value: " + formatValue(value1);
            case DiffNode.TYPE_REMOVED:
                return "Property '" + key + "' was removed";
            case DiffNode.TYPE_CHANGED:
                return "Property '" + key + "' was updated. From " + formatValue(value1) + " to " + formatValue(value2);
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }

        return value.toString();
    }
}
