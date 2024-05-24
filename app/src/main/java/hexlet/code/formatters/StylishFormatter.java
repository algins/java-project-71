package hexlet.code.formatters;

import java.io.IOException;
import java.util.ArrayList;

import hexlet.code.DiffNode;
import hexlet.code.DiffTree;
import hexlet.code.utils.StringUtils;

public class StylishFormatter {
    public static String format(DiffTree diffTree) throws IOException {
        var parts = new ArrayList<String>();
        parts.add("{");

        for (DiffNode node : diffTree.getNodes()) {
            parts.add(formatNode(node));
        }

        parts.add("}");
        return String.join("\n", parts);
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
