package hexlet.code.formatters;

import java.io.IOException;

import hexlet.code.DiffTree;

public class Formatter {
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_STYLISH = "stylish";

    public static String format(DiffTree diffTree, String format) throws IOException {
        switch (format) {
            case FORMAT_PLAIN:
                return PlainFormatter.format(diffTree);
            case FORMAT_STYLISH:
                return StylishFormatter.format(diffTree);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
