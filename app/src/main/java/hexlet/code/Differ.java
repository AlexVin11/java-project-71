package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    static final String[] KEYSTATUS = {"key edited", "key not edited"};
    static final Character[] EDITSIGN = {'+', '-'};

    public static HashMap<String, Object> generateHashMapFromFileContent(String fileContent)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> fileContentAsHashMap
                = objectMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
        return (HashMap<String, Object>) fileContentAsHashMap;
    }

    public static SortedMap<String, String> generateKeyStatusHashMap(HashMap<String, Object> firstFile,
                                                                     HashMap<String, Object> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();
        for (var secondFileEntry : secondMapEntrys) {
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[1]);
            }
            if (!firstFile.containsKey(secondFileEntry.getKey())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
            if (firstFile.containsKey(secondFileEntry.getKey())
                    && !firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) {
                mapOfKeysStatus.put(firstFileEntry.getKey(), KEYSTATUS[0]);
            }
        }
        return mapOfKeysStatus;
    }

    public static String generate(String firstFile, String secondFile) throws IOException {
        Path firstPath = Path.of(firstFile).toAbsolutePath().normalize();
        Path secondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String firstFileContent = Files.readString(firstPath);
        String secondFileContent  = Files.readString(secondPath);
        HashMap<String, Object> firstFileAsHashMap = generateHashMapFromFileContent(firstFileContent);
        HashMap<String, Object> secondFileAsHashMap = generateHashMapFromFileContent(secondFileContent);
        SortedMap<String, String> differenceMap = generateKeyStatusHashMap(firstFileAsHashMap, secondFileAsHashMap);
        StringBuilder resultMessage = new StringBuilder("{");
        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEYSTATUS[1])) {
                resultMessage.append("\n" + " ".repeat(4) + key + ": " + firstFileAsHashMap.get(key) + ", ");
            }
            if (differenceMap.get(key).equals(KEYSTATUS[0])) {
                if (firstFileAsHashMap.containsKey(key)) {
                    resultMessage.append("\n"
                            + " ".repeat(2) + EDITSIGN[1]
                            + " " + key + ": " + firstFileAsHashMap.get(key) + ", ");
                }
                if (secondFileAsHashMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[0]
                            + " " + key + ": " + secondFileAsHashMap.get(key) + ", ");
                }
            }
        }
        resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
        return resultMessage.toString();
    }
}
