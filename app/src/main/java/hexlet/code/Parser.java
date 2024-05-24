package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {

    public static Map<String, Object> parseFileContentToMap(String content) throws Exception {
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

    public static Map<String, String> convertMapValuesToString(Map<String, Object> fileContentAsMap) {
        Map<String, String> resultMap = new HashMap<>();
        var fileContentEntrySet = fileContentAsMap.entrySet();
        for (var fileContentEntry : fileContentEntrySet) {
            resultMap.put(fileContentEntry.getKey(), String.valueOf(fileContentEntry.getValue()));
        }
        return resultMap;
    }
}
