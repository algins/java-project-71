package hexlet.code.formatters;

import java.io.IOException;
import java.util.stream.Collectors;

import hexlet.code.DiffNode;
import hexlet.code.DiffTree;
import hexlet.code.utils.StringUtils;

public class StylishFormatter {
    public static String format(DiffTree diffTree) throws IOException {
        var formattedNodes = diffTree.getNodes().stream()
            .map(StylishFormatter::formatNode)
            .collect(Collectors.joining("\n"));

        return "{\n" + formattedNodes + "\n}";
    }

    private static String formatNode(DiffNode node) {
        var type = node.getType();
        var key = node.getKey();
        var value1 = node.getValue1();
        var value2 = node.getValue2();

        switch (type) {
            case DiffNode.TYPE_ADDED:
                return StringUtils.indent("+ " + key + ": " + value1);
            case DiffNode.TYPE_REMOVED:
                return StringUtils.indent("- " + key + ": " + value1);
            case DiffNode.TYPE_UNCHANGED:
                return StringUtils.indent(key + ": " + value1, 4);
            case DiffNode.TYPE_CHANGED:
                return StringUtils.indent("- " + key + ": " + value1 + "\n  + " + key + ": " + value2);
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
