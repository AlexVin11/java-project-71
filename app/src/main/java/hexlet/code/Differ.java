package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class Differ{
    public static HashMap generate(Path p1, Path p2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String firstFileContent = Files.readString(p1);
        String secondFileContent = Files.readString(p2);
        HashMap<String, Object> firstFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(firstFileContent, new TypeReference<Map<String,Object>>(){});
        HashMap<String, Object> secondFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(secondFileContent, new TypeReference<Map<String,Object>>(){});
        var resultMap = new HashMap<String, Object>();
        var firstFileEntrys = firstFileAsMap.entrySet();
        var secondFileEntrys = secondFileAsMap.entrySet();
        for (var entryFirstFile : firstFileEntrys) {
            if (!secondFileAsMap.containsKey(entryFirstFile.getKey())) {
                String keyToAddFromFirstFile = "- " + entryFirstFile.getKey();
                resultMap.put(keyToAddFromFirstFile, entryFirstFile.getValue());
            }
            if (secondFileAsMap.containsKey(entryFirstFile.getKey())
                    && secondFileAsMap.get(entryFirstFile.getKey()).equals(entryFirstFile.getValue())) {
                resultMap.put(entryFirstFile.getKey(), entryFirstFile.getValue());
            }
        }
        for (var secondFileEntry : secondFileEntrys) {
            if (!firstFileAsMap.containsKey(secondFileEntry.getKey())) {
                String keyToAddFromSecondFile = "+ " + secondFileEntry.getKey();
                resultMap.put(keyToAddFromSecondFile, secondFileEntry.getValue());
            }
            if (firstFileAsMap.containsKey(secondFileEntry.getKey())
                    && !firstFileAsMap.get(secondFileEntry.getKey()).equals(secondFileEntry.getValue())) {
                String oldKey = "- " + secondFileEntry.getKey();
                String newKey = "+ " + secondFileEntry.getKey();
                resultMap.put(oldKey, firstFileAsMap.get(secondFileEntry.getKey()));//
                resultMap.put(newKey, secondFileEntry.getValue());
            }
        }
        return resultMap;
    }
}
