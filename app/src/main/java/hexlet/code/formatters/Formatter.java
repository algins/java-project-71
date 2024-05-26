package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;

import hexlet.code.DiffItem;

public class Formatter {
    public static final String FORMAT_JSON = "json";
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_STYLISH = "stylish";

    public static String format(List<DiffItem> diff, String format) throws IOException {
        switch (format) {
            case FORMAT_JSON:
                return JsonFormatter.format(diff);
            case FORMAT_PLAIN:
                return PlainFormatter.format(diff);
            case FORMAT_STYLISH:
                return StylishFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
