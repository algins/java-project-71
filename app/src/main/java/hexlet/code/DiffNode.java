package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiffNode {
    public static final String TYPE_ADDED = "added";
    public static final String TYPE_REMOVED = "removed";
    public static final String TYPE_CHANGED = "changed";
    public static final String TYPE_UNCHANGED = "unchanged";

    private String type;
    private String key;
    private Object value1;
    private Object value2;

    public DiffNode(String type, String key, Object value1) {
        this.type = type;
        this.key = key;
        this.value1 = value1;
    }
}
