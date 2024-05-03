package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ{

    static final String[] KEYSTATUS = {"key edited", "key not edited"};
    static final Character[] EDITSIGN = {'+', '-'};

    public static String generate(Path p1, Path p2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String firstFileContent = Files.readString(p1);
        String secondFileContent = Files.readString(p2);
        HashMap<String, Object> firstFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(firstFileContent, new TypeReference<Map<String, Object>>() {
        });
        var firstMapEntrys = firstFileAsMap.entrySet();
        HashMap<String, Object> secondFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(secondFileContent, new TypeReference<Map<String, Object>>() {
        });
        var secondMapEntrys = secondFileAsMap.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();
        for (var secondFileEntry : secondMapEntrys) {
            if (firstFileAsMap.containsKey(secondFileEntry.getKey()) //key and value in both maps are equal - means just show it in result map
                    && firstFileAsMap.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[1]);
            }
            if (!firstFileAsMap.containsKey(secondFileEntry.getKey())) { //key from second file absent in first file - means it was added
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
            if (firstFileAsMap.containsKey(secondFileEntry.getKey()) //key is present in both files but values are different - means it was edited
                    && !firstFileAsMap.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFileAsMap.containsKey(firstFileEntry.getKey())) { //key from first file is absent in second file - means it was deleted from first file
                mapOfKeysStatus.put(firstFileEntry.getKey(), KEYSTATUS[0]);
            }
        }
        StringBuilder resultMessage = new StringBuilder("{");
        for (String key : mapOfKeysStatus.keySet()) {
            if (mapOfKeysStatus.get(key).equals(KEYSTATUS[1])) {
                resultMessage.append("\n" + " ".repeat(4) + key + ": " + firstFileAsMap.get(key) + ", ");
            }
            if (mapOfKeysStatus.get(key).equals(KEYSTATUS[0])) {
                if (firstFileAsMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[1] + " " + key + ": " + firstFileAsMap.get(key) + ", ");
                }
                if (secondFileAsMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[0] + " " + key + ": " + secondFileAsMap.get(key) + ", ");
                }
            }
        }
        resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
        String result = resultMessage.toString();
        return result;
    }
}
