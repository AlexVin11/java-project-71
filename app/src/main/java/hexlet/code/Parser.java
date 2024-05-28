package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFileContentToMap(String content, String type) throws Exception {
        if (type.equals("json")) {
            return parseJson(content);
        } else {
            return parseYaml(content);
        }
    }

    public static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        return yamlMapper.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(content, new TypeReference<>() {
        });
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
