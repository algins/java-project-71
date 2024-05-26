package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import hexlet.code.DiffItem;
import hexlet.code.utils.StringUtils;

public class StylishFormatter {
    public static String format(List<DiffItem> diff) throws IOException {
        var formattedItems = diff.stream()
            .map(StylishFormatter::formatItem)
            .collect(Collectors.joining("\n"));

        return "{\n" + formattedItems + "\n}";
    }

    private static String formatItem(DiffItem item) {
        var type = item.getType();
        var key = item.getKey();
        var value1 = item.getValue1();
        var value2 = item.getValue2();

        switch (type) {
            case DiffItem.TYPE_ADDED:
                return StringUtils.indent("+ " + key + ": " + value1);
            case DiffItem.TYPE_REMOVED:
                return StringUtils.indent("- " + key + ": " + value1);
            case DiffItem.TYPE_UNCHANGED:
                return StringUtils.indent(key + ": " + value1, 4);
            case DiffItem.TYPE_CHANGED:
                return StringUtils.indent("- " + key + ": " + value1 + "\n  + " + key + ": " + value2);
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
