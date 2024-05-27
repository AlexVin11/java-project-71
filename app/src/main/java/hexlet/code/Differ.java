package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.SortedMap;

public class Differ {

    public static String generate(String firstFile, String secondFile) throws Exception {
        return generate(firstFile, secondFile, "stylish");
    }

    public static String generate(String firstFile, String secondFile, String format) throws Exception {
        Path processedFirstPath = Path.of(firstFile).toAbsolutePath().normalize();
        String firstFileType = FilenameUtils.getExtension(processedFirstPath.toString());
        Path processedSecondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String secondFileType = FilenameUtils.getExtension(processedSecondPath.toString());
        String firstFileContent = Files.readString(processedFirstPath);
        String secondFileContent  = Files.readString(processedSecondPath);
        Map<String, Object> firstFileAsHashMap = Parser.parseFileContentToMap(firstFileContent, firstFileType);
        Map<String, Object> secondFileAsHashMap = Parser.parseFileContentToMap(secondFileContent, secondFileType);
        SortedMap<String, String> differenceMap = Comparator.generateKeyStatusHashMap(firstFileAsHashMap,
                secondFileAsHashMap);
        return Formatter.generateFormatedMessage(firstFileAsHashMap, secondFileAsHashMap, differenceMap, format);
    }
}
