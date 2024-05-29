package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class Json {

    public static String generateJson(Map<String, Object> differenceMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(differenceMap);
        return jsonData;
    }
}
