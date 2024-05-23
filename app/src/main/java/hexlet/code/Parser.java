package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> parse(String text, String type) throws IOException {
        switch (type.toLowerCase()) {
            case "json":
                var objectMapper = new ObjectMapper();
                return objectMapper.readValue(text, new TypeReference<Map<String, Object>>() { });
            case "yaml":
            case "yml":
                var yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(text, new TypeReference<Map<String, Object>>() { });
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
