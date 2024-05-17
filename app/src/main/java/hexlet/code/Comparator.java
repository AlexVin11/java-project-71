package hexlet.code;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static hexlet.code.Differ.EDIT_SIGN;
import static hexlet.code.Differ.KEY_STATUS;

public class Comparator {

    public static SortedMap<String, String> generateKeyStatusHashMap(Map<String, String> firstFile,
                                                                     Map<String, String> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();

        for (var secondFileEntry : secondMapEntrys) {
            /*1*/if (firstFile.containsKey(secondFileEntry.getKey())
                    && firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[1]);//key not edited (key and value is the same in both files)
            }
            /*3*/if (!firstFile.containsKey(secondFileEntry.getKey())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[2]);//key added (key from second file is absent in first file)
            }
            /*4*/if (firstFile.containsKey(secondFileEntry.getKey())
                    && !firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);//value by key is edited (key is present in both files but values are different)
            }
            /*2*/for (var firstFileEntry : firstMapEntrys) {
                if (!secondFile.containsKey(firstFileEntry.getKey())) {
                    mapOfKeysStatus.put(firstFileEntry.getKey(), KEY_STATUS[3]);//key removed (key from first file absent in second file)
                }
            }
        }
        /*for (var secondFileEntry : secondMapEntrys) {
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[1]);
            }
            if (!firstFile.containsKey(secondFileEntry.getKey())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);
            }
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && !firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEY_STATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) {
                mapOfKeysStatus.put(firstFileEntry.getKey(), KEY_STATUS[0]);
            }
        }*/
        return mapOfKeysStatus;
    }
}
