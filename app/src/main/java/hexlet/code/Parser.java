package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static final String TYPE_JSON = "json";
    public static final String TYPE_YAML = "yaml";
    public static final String TYPE_YML = "yml";

    public static Map<String, Object> parse(String text, String type) throws IOException {
        switch (type.toLowerCase()) {
            case TYPE_JSON:
                var objectMapper = new ObjectMapper();
                return objectMapper.readValue(text, new TypeReference<Map<String, Object>>() { });
            case TYPE_YAML:
            case TYPE_YML:
                var yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(text, new TypeReference<Map<String, Object>>() { });
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
