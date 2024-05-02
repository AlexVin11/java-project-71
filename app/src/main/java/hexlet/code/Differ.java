package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Differ{
    public static Map/*String*//*Map*//*MapDifference*/ generate(Path p1, Path p2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String firstFileContent = Files.readString(p1);
        String secondFileContent = Files.readString(p2);
        /*String processedFirstFileContent = firstFileContent.replaceAll("[{}]", "");
        String processedSecondFileContent = secondFileContent.replaceAll("[{}]", "");
        String[] firstFileAsArray = processedFirstFileContent.split(": ");
        String[] secondFileAsArray = processedSecondFileContent.split(": ");
        var result = new StringBuilder();
        var firstList = Arrays.asList(firstFileAsArray);
        var secondList = Arrays.asList(secondFileAsArray);*/
        HashMap<String, Object> firstFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(firstFileContent, new TypeReference<Map<String,Object>>(){});
        /*var sortedFirstMap = firstFileAsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey());*/
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
                String oldKey = "- " + secondFileEntry.getKey();//firstFileAsMap.get(secondFileEntry.getKey());//
                String newKey = "+ " + secondFileEntry.getKey();
                resultMap.put(oldKey, firstFileAsMap.get(secondFileEntry.getKey()));//
                resultMap.put(newKey, secondFileEntry.getValue());
            }

            /*var sortedResultMap = resultMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Object>comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));*/
        }
        TreeMap<String, Object> sorted = new TreeMap<>(resultMap);
        //var diff = Maps.difference(firstFileAsMap, secondFileAsMap);
        //System.out.println(firstFileAsMap);
        //System.out.println(secondFileAsMap);
        //System.out.println(diff);
        //return firstFileAsMap;
        //return firstFileContent;
        //return resultMap;
        return (Map) sorted;
    }
}
