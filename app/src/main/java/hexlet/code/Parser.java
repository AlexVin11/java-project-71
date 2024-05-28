package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFileContentToMap(String content, String type) throws Exception {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper ymlMapper = new XmlMapper();

        if (type.equals("yaml")) {
            return yamlMapper.readValue(content, new TypeReference<>() {
            });
        } else if (type.equals("yml")) {
            return ymlMapper.readValue(content, new TypeReference<>() {
            });
        } else {
            return jsonMapper.readValue(content, new TypeReference<>() {
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
