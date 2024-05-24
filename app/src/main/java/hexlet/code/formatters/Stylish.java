package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Comparator.KEY_STATUS;

public class Stylish {

    public static final int EDITED_LINE_SPACE_COUNT = 2;
    public static final int NOT_EDITED_LINE_SPACE_COUNT = 4;
    public static final Character[] EDIT_SIGN = {'+', '-'};

    public static String generateStylishOutput(Map<String, Object> firstFileAsHashMap,
                                               Map<String, Object> secondFileAsHashMap,
                                               SortedMap<String, String> differenceMap) {
        StringBuilder resultMessage = new StringBuilder("{");

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEY_STATUS[1])) {
                resultMessage.append("\n"
                        + " ".repeat(NOT_EDITED_LINE_SPACE_COUNT) + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + EDIT_SIGN[1]
                        + " " + key + ": " + firstFileAsHashMap.get(key));
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + EDIT_SIGN[0]
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[2])) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + EDIT_SIGN[1]
                        + " " + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(KEY_STATUS[3])) {
                resultMessage.append("\n" + " ".repeat(EDITED_LINE_SPACE_COUNT) + EDIT_SIGN[0]
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
        }
        resultMessage.append("\n" + "}");
        return resultMessage.toString();
    }
}
