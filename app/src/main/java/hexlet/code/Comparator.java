package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static hexlet.code.Differ.EDIT_SIGN;
import static hexlet.code.Differ.KEY_STATUS;

public class Comparator {

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
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);
            }
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && !Objects.equals(firstFile.get(secondFileEntry.getKey()), secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) {
                mapOfKeysStatus.put(firstFileEntry.getKey(), KEY_STATUS[0]);
            }
        }
        return mapOfKeysStatus;
    }
}
