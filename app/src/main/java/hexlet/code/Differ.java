package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.SortedMap;

public class Differ {

    static final String[] KEY_STATUS = {"key edited", "key not edited"};
    static final Character[] EDIT_SIGN = {'+', '-'};

    public static String generate(String firstFile, String secondFile) throws Exception {
        return generate(firstFile, secondFile, "stylish");
    }

    public static String generate(String firstFile, String secondFile, String format) throws Exception {
        Path peocessedFirstPath = Path.of(firstFile).toAbsolutePath().normalize();
        Path processedSecondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String firstFileContent = Files.readString(peocessedFirstPath);
        String secondFileContent  = Files.readString(processedSecondPath);
        Map<String, Object> firstFileAsHashMap = Parser.parser(firstFileContent);
        Map<String, Object> secondFileAsHashMap = Parser.parser(secondFileContent);
        SortedMap<String, String> differenceMap = Comparator.generateKeyStatusHashMap(firstFileAsHashMap,
                secondFileAsHashMap);

        if (format.equals("stylish")) {
            return Formatter.stylish(firstFileAsHashMap, secondFileAsHashMap, differenceMap);
        } else {
            return "plain was selected";
        }
    }
}
