package hexlet.code.utils;

public class StringUtils {
    public static String indent(String str) {
        var spacesCount = 2;
        return indent(str, spacesCount);
    }

    public static String indent(String str, int spacesCount) {
        return " ".repeat(spacesCount) + str;
    }
}
