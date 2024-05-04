package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ{

    static final String[] KEYSTATUS = {"key edited", "key not edited"};
    static final Character[] EDITSIGN = {'+', '-'};

    public static Path generatePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    public static String readFileContent(Path path) throws IOException {
        return Files.readString(path);
    }

    public static HashMap<String, Object> generateHashMapFromFileContent(String fileContent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> fileContentAsHashMap
                = (HashMap<String, Object>) objectMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {
        });
        return fileContentAsHashMap;
    }

    public static SortedMap<String, String> generateKeyStatusHashMap(HashMap<String, Object> firstFile,
                                                                     HashMap<String, Object> secondFile) {
        var firstMapEntrys = firstFile.entrySet();
        var secondMapEntrys = secondFile.entrySet();
        SortedMap<String, String> mapOfKeysStatus = new TreeMap<>();
        for (var secondFileEntry : secondMapEntrys) {
            if (firstFile.containsKey(secondFileEntry.getKey()) //key and value in both maps are equal - means just show it in result map
                    && firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[1]);
            }
            if (!firstFile.containsKey(secondFileEntry.getKey())) { //key from second file absent in first file - means it was added
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
            if (firstFile.containsKey(secondFileEntry.getKey()) //key is present in both files but values are different - means it was edited
                    && !firstFile.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                mapOfKeysStatus.put(secondFileEntry.getKey(), KEYSTATUS[0]);
            }
        }
        for (var firstFileEntry : firstMapEntrys) {
            if (!secondFile.containsKey(firstFileEntry.getKey())) { //key from first file is absent in second file - means it was deleted from first file
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
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[1] + " " + key + ": " + firstMap.get(key) + ", ");
                }
                if (secondMap.containsKey(key)) {
                    resultMessage.append("\n" + " ".repeat(2) + EDITSIGN[0] + " " + key + ": " + secondMap.get(key) + ", ");
                }
            }
        }
        resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
        return resultMessage.toString();
    }
}
