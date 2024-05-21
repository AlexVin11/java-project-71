package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Differ.EDIT_SIGN;
import static hexlet.code.Differ.KEY_STATUS;

public class Stylishcopy {

    public static String generateStylishOutputCopy(Map<String, Object> firstFileAsHashMap,
                                                   Map<String, Object> secondFileAsHashMap,
                                                   SortedMap<String, String> differenceMap) {
        StringBuilder resultMessage = new StringBuilder("{");

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEY_STATUS[1])) {
                resultMessage.append("\n"
                        + " ".repeat(4) + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                resultMessage.append("\n"
                        + " ".repeat(2) + EDIT_SIGN[1]
                        + " " + key + ": " + firstFileAsHashMap.get(key));
                resultMessage.append("\n"
                        + " ".repeat(2) + EDIT_SIGN[0]
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[2])) {
                resultMessage.append("\n"
                        + " ".repeat(2) + EDIT_SIGN[1]
                        + " " + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[3])) {
                resultMessage.append("\n" + " ".repeat(2) + EDIT_SIGN[0]
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
        }
        resultMessage.append("\n" + "}");
        return resultMessage.toString();
    }
}