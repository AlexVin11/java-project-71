package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, String> parser(String content) throws Exception {
        Object obj = new Object();
        if (content.startsWith("---")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(content, new TypeReference<>() {
            });
        } else {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, new TypeReference<>() {
            });
        }
    }
}
