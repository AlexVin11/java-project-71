package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.SortedMap;

public class Differ {

    static final String[] KEY_STATUS = {"key edited", "key not edited", "key added", "key removed"};
    static final Character[] EDIT_SIGN = {'+', '-'};

    public static String generate(String firstFile, String secondFile) throws Exception {
        return generate(firstFile, secondFile, "stylish");
    }

    public static String generate(String firstFile, String secondFile, String format) throws Exception {
        Path peocessedFirstPath = Path.of(firstFile).toAbsolutePath().normalize();
        Path processedSecondPath = Path.of(secondFile).toAbsolutePath().normalize();
        String firstFileContent = Files.readString(peocessedFirstPath);
        String secondFileContent  = Files.readString(processedSecondPath);
        Map<String, String> firstFileAsHashMap = Parser.parser(firstFileContent);
        Map<String, String> secondFileAsHashMap = Parser.parser(secondFileContent);
        SortedMap<String, String> differenceMap = Comparator.generateKeyStatusHashMap(firstFileAsHashMap, secondFileAsHashMap);

        if (format.equals("stylish")) {
            StringBuilder resultMessage = new StringBuilder("{");
            for (String key : differenceMap.keySet()) {
                if (differenceMap.get(key).equals(KEY_STATUS[1])) {
                    resultMessage.append("\n" + " ".repeat(4) + key + ": " + firstFileAsHashMap.get(key) + ", ");
                }
                if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                    if (firstFileAsHashMap.containsKey(key)) {
                        resultMessage.append("\n"
                                + " ".repeat(2) + EDIT_SIGN[1]
                                + " " + key + ": " + firstFileAsHashMap.get(key) + ", ");
                    }
                    if (secondFileAsHashMap.containsKey(key)) {
                        resultMessage.append("\n" + " ".repeat(2) + EDIT_SIGN[0]
                                + " " + key + ": " + secondFileAsHashMap.get(key) + ", ");
                    }
                }
            }
            resultMessage.delete(resultMessage.length() - 2, resultMessage.length()).append("\n" + "}");
            return resultMessage.toString();
        } else {
            return "plain was selected";
        }
    }
}
