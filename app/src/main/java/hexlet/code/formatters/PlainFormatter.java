package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hexlet.code.DiffItem;

public class PlainFormatter {
    public static String format(List<DiffItem> diff) throws IOException {
        return diff.stream()
            .filter(item -> !item.getType().equals(DiffItem.TYPE_UNCHANGED))
            .map(PlainFormatter::formatItem)
            .collect(Collectors.joining("\n"));
    }

    private static String formatItem(DiffItem item) {
        var type = item.getType();
        var key = item.getKey();
        var value1 = item.getValue1();
        var value2 = item.getValue2();

        switch (type) {
            case DiffItem.TYPE_ADDED:
                return "Property '" + key + "' was added with value: " + formatValue(value1);
            case DiffItem.TYPE_REMOVED:
                return "Property '" + key + "' was removed";
            case DiffItem.TYPE_CHANGED:
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
