package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Differ.EDIT_SIGN;
import static hexlet.code.Differ.KEY_STATUS;

public class Json {

    public static String generateJson(Map<String, Object> firstFileAsHashMap,
                                      Map<String, Object> secondFileAsHashMap,
                                      SortedMap<String, String> differenceMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEY_STATUS[1])) {
                map.put(key, String.valueOf(firstFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                map.put(EDIT_SIGN[1]
                        + key, String.valueOf(firstFileAsHashMap.get(key)));
                map.put(EDIT_SIGN[0]
                        + key, String.valueOf(secondFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[2])) {
                map.put(EDIT_SIGN[1]
                        + key, String.valueOf(firstFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[3])) {
                map.put(EDIT_SIGN[0]
                        + key, String.valueOf(secondFileAsHashMap.get(key)));
            }
        }
        String jsonData = mapper.writeValueAsString(map);
        return jsonData;
    }
}
