package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Comparator.*;
import static hexlet.code.formatters.Stylish.EDIT_SIGN;

public class Json {

    public static String generateJson(Map<String, Object> firstFileAsHashMap,
                                      Map<String, Object> secondFileAsHashMap,
                                      SortedMap<String, String> differenceMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(NOT_EDITED)) {
                map.put(key, String.valueOf(firstFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(EDITED)) {
                map.put(EDIT_SIGN[1]
                        + key, String.valueOf(firstFileAsHashMap.get(key)));
                map.put(EDIT_SIGN[0]
                        + key, String.valueOf(secondFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(REMOVED)) {
                map.put(EDIT_SIGN[1]
                        + key, String.valueOf(firstFileAsHashMap.get(key)));
            }
            if (differenceMap.get(key).equals(ADDED)) {
                map.put(EDIT_SIGN[0]
                        + key, String.valueOf(secondFileAsHashMap.get(key)));
            }
        }
        String jsonData = mapper.writeValueAsString(map);
        return jsonData;
    }
}
