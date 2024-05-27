package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Comparator.*;

public class Stylish {

    public static final int EDITED_LINE_SPACE_COUNT = 2;
    public static final int NOT_EDITED_LINE_SPACE_COUNT = 4;
    public static final char PLUS = '+';
    public static final char MINUS = '-';

    public static String generateStylishOutput(Map<String, Object> firstFileAsHashMap,
                                               Map<String, Object> secondFileAsHashMap,
                                               SortedMap<String, String> differenceMap) {
        StringBuilder resultMessage = new StringBuilder("{");

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(NOT_EDITED)) {
                resultMessage.append("\n"
                        + " ".repeat(NOT_EDITED_LINE_SPACE_COUNT) + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(EDITED)) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + MINUS
                        + " " + key + ": " + firstFileAsHashMap.get(key));
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + PLUS
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(REMOVED)) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + MINUS
                        + " " + key + ": " + firstFileAsHashMap.get(key));
            }
            if (differenceMap.get(key).equals(ADDED)) {
                resultMessage.append("\n" + " ".repeat(EDITED_LINE_SPACE_COUNT) + PLUS
                        + " " + key + ": " + secondFileAsHashMap.get(key));
            }
        }
        resultMessage.append("\n" + "}");
        return resultMessage.toString();
    }
}
