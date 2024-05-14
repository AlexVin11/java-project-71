package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    static final String[] KEY_STATUS = {"key edited", "key not edited"};
    static final Character[] EDIT_SIGN = {'+', '-'};

    public static SortedMap<String, String> generateKeyStatusHashMap(HashMap<String, Object> firstFile,
                                                                     HashMap<String, Object> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();

        for (var secondFileEntry : secondMapEntrys) {
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
        }
        return mapOfKeysStatus;
    }

    public static String generate(String firstFile, String secondFile) throws Exception {
        Path peocessedFirstPath = Path.of(firstFile).toAbsolutePath().normalize();
        Path processedSecondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String firstFileContent = Files.readString(peocessedFirstPath);
        String secondFileContent  = Files.readString(processedSecondPath);
        HashMap<String, Object> firstFileAsHashMap = Parser.parser(firstFileContent);
        HashMap<String, Object> secondFileAsHashMap = Parser.parser(secondFileContent);
        SortedMap<String, String> differenceMap = generateKeyStatusHashMap(firstFileAsHashMap, secondFileAsHashMap);

        StringBuilder resultMessage = new StringBuilder("{");
        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEY_STATUS[1])) {
                resultMessage.append("\n" + " ".repeat(4) + key + ": " + firstFileAsHashMap.get(key) + ", ");
            }
            if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                if (firstFileAsHashMap.containsKey(key)) {
                    resultMessage.append("\n"
                            + " ".repeat(2) + EDIT_SIGN[1]
                            + " " + key + ": " + firstFileAsHashMap.get(key) + ", ");
                }
                if (secondFileAsHashMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDIT_SIGN[0]
                            + " " + key + ": " + secondFileAsHashMap.get(key) + ", ");
                }
            }
        }
        resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
        return resultMessage.toString();
    }
}
