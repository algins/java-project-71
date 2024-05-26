package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import hexlet.code.DiffItem;

public class JsonFormatter {
    public static String format(List<DiffItem> diff) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
}
