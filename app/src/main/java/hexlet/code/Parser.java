package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;

public class Parser {

    public static HashMap<String, Object> parser(String content) throws Exception {
        HashMap<String, Object> contentAsMap = new HashMap<>();
        if (content.startsWith("---")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            contentAsMap = mapper.readValue(content, HashMap.class);
            return contentAsMap;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            contentAsMap = mapper.readValue(content, HashMap.class);
            return contentAsMap;
        }
    }
}
