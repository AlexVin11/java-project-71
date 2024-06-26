package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Comparator {

    public static final String EDITED = "edited";
    public static final String NOT_EDITED = "not edited";
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";

    public static Map<String, Object> generateKeyStatusHashMap(Map<String, Object> firstFile,
                                                               Map<String, Object> secondFile) {
        Set<String> keys = new TreeSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        Map<String, Object> mapOfKeysStatus = new LinkedHashMap<>();

        for (String key : keys) {
            if (firstFile.containsKey(key) && !secondFile.containsKey(key)) {
                Object value = !Objects.isNull(firstFile.get(key)) ? firstFile.get(key) : null;
                mapOfKeysStatus.put(key, new ValueAndState(REMOVED,
                        value, null));
            }
            if (secondFile.containsKey(key) && !firstFile.containsKey(key)) {
                Object value = !Objects.isNull(secondFile.get(key)) ? secondFile.get(key) : null;
                mapOfKeysStatus.put(key, new ValueAndState(ADDED,
                        null, value));
            }
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                Object firstValue = !Objects.isNull(firstFile.get(key)) ? firstFile.get(key) : null;
                Object secondValue = !Objects.isNull(secondFile.get(key)) ? secondFile.get(key) : null;
                if (Objects.equals(firstValue, secondValue)) {
                    mapOfKeysStatus.put(key, new ValueAndState(NOT_EDITED,
                            firstValue, secondValue));
                } else {
                    mapOfKeysStatus.put(key, new ValueAndState(EDITED,
                            firstValue, secondValue));
                }
            }
        }
        return mapOfKeysStatus;
    }
}
