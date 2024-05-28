package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static hexlet.code.ValueState.ADDED;
import static hexlet.code.ValueState.EDITED;
import static hexlet.code.ValueState.NOT_EDITED;
import static hexlet.code.ValueState.REMOVED;


public class Comparator {

    public static SortedMap<String, String> generateKeyStatusHashMap(Map<String, Object> firstFile,
                                                                     Map<String, Object> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();

        for (var secondFileEntry : secondMapEntrys) {
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && Objects.equals(firstFile.get(secondFileEntry.getKey()), secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), NOT_EDITED);
            }
            if (!firstFile.containsKey(secondFileEntry.getKey())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), ADDED);
            }
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && !Objects.equals(firstFile.get(secondFileEntry.getKey()), secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), EDITED);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) {
                mapOfKeysStatus.put(firstFileEntry.getKey(), REMOVED);
            }
        }
        return mapOfKeysStatus;
    }
}
