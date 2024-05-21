package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.SortedMap;

public class Differ {

    public static final String[] KEY_STATUS = {"key edited", "key not edited", "key removed", "key added"};
    public static final Character[] EDIT_SIGN = {'+', '-'};

    public static String generate(String firstFile, String secondFile) throws Exception {
        return generate(firstFile, secondFile, "stylish");
    }

    public static String generate(String firstFile, String secondFile, String format) throws Exception {
        Path peocessedFirstPath = Path.of(firstFile).toAbsolutePath().normalize();
        Path processedSecondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String firstFileContent = Files.readString(peocessedFirstPath);
        String secondFileContent  = Files.readString(processedSecondPath);
        Map<String, Object> firstFileAsHashMap = Parser.parseFileContentToMap(firstFileContent);
        Map<String, Object> secondFileAsHashMap = Parser.parseFileContentToMap(secondFileContent);
        SortedMap<String, String> differenceMap = Comparator.generateKeyStatusHashMap(firstFileAsHashMap,
                secondFileAsHashMap);

        if (format.equals("stylish")) {
            return Stylish.generateStylishOutputCopy(firstFileAsHashMap,
                    secondFileAsHashMap, differenceMap);
        } else {
            return Plain.generatePlainOutput(firstFileAsHashMap, secondFileAsHashMap, differenceMap);
        }
    }
}
