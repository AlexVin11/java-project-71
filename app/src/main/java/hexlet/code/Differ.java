package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class Differ{
    public static String generate(Path p1, Path p2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String firstFileContent = Files.readString(p1);
        String secondFileContent = Files.readString(p2);
        Map<String, Object> firstFileAsMap
                = objectMapper.readValue(firstFileContent, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> secondFileAsMap
                = objectMapper.readValue(secondFileContent, new TypeReference<Map<String,Object>>(){});
        return "generate created";
    }
}
