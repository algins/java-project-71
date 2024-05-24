package hexlet.code.utils;

public class StringUtils {
    public static String indent(String str) {
        return indent(str, 2);
    }

    public static String indent(String str, int spacesCount) {
        return " ".repeat(spacesCount) + str;
    }
}
