package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    static final String[] KEYSTATUS = {"key edited", "key not edited"};
    static final Character[] EDITSIGN = {'+', '-'};

    public static Path generatePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    public static String readFileContent(Path path) throws IOException {
        return Files.readString(path);
    }

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

    public static String generate(SortedMap<String, String> mapOfKeyStatus,
                                  HashMap<String, Object> firstMap, HashMap<String, Object> secondMap) {
        StringBuilder resultMessage = new StringBuilder("{");
        for (String key : mapOfKeyStatus.keySet()) {
            if (mapOfKeyStatus.get(key).equals(KEYSTATUS[1])) {
                resultMessage.append("\n" + " ".repeat(4) + key + ": " + firstMap.get(key) + ", ");
            }
            if (mapOfKeyStatus.get(key).equals(KEYSTATUS[0])) {
                if (firstMap.containsKey(key)) {
                    resultMessage.append("\n"
                            + " ".repeat(2) + EDITSIGN[1]
                            + " " + key + ": " + firstMap.get(key) + ", ");
                }
                if (secondMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[0]
                            + " " + key + ": " + secondMap.get(key) + ", ");
                }
            }
        }
        resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
        return resultMessage.toString();
    }
}
