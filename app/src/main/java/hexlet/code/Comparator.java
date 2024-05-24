package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;


public class Comparator {

    public static final String[] KEY_STATUS = {"key edited", "key not edited", "key removed", "key added"};

    public static SortedMap<String, String> generateKeyStatusHashMap(Map<String, Object> firstFile,
                                                                     Map<String, Object> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();

        for (var secondFileEntry : secondMapEntrys) {
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && Objects.equals(firstFile.get(secondFileEntry.getKey()), secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[1]);
            }
            if (!firstFile.containsKey(secondFileEntry.getKey())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[3]);
            }
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && !Objects.equals(firstFile.get(secondFileEntry.getKey()), secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) {
                mapOfKeysStatus.put(firstFileEntry.getKey(), KEY_STATUS[2]);
            }
        }
        return mapOfKeysStatus;
    }
}
