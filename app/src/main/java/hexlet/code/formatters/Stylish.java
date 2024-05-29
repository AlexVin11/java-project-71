package hexlet.code.formatters;

import hexlet.code.ValueAndState;

import java.util.Map;

import static hexlet.code.ValueAndState.ADDED;
import static hexlet.code.ValueAndState.EDITED;
import static hexlet.code.ValueAndState.NOT_EDITED;
import static hexlet.code.ValueAndState.REMOVED;


public class Stylish {

    public static final int EDITED_LINE_SPACE_COUNT = 2;
    public static final int NOT_EDITED_LINE_SPACE_COUNT = 4;
    public static final char PLUS = '+';
    public static final char MINUS = '-';

    public static String generateStylishOutput(Map<String, Object> differenceMap) {
        StringBuilder resultMessage = new StringBuilder("{");

        for (var entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            ValueAndState value = (ValueAndState) entry.getValue();
            String status = value.getKeyStatus();
            if (status.equals(NOT_EDITED)) {
                resultMessage.append("\n"
                        + " ".repeat(NOT_EDITED_LINE_SPACE_COUNT) + key + ": " + value.getOldValue());
            }
            if (status.equals(EDITED)) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + MINUS
                        + " " + key + ": " + value.getOldValue());
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + PLUS
                        + " " + key + ": " + value.getNewValue());
            }
            if (status.equals(REMOVED)) {
                resultMessage.append("\n"
                        + " ".repeat(EDITED_LINE_SPACE_COUNT) + MINUS
                        + " " + key + ": " + value.getOldValue());
            }
            if (status.equals(ADDED)) {
                resultMessage.append("\n" + " ".repeat(EDITED_LINE_SPACE_COUNT) + PLUS
                        + " " + key + ": " + value.getNewValue());
            }
        }
        resultMessage.append("\n" + "}");
        return resultMessage.toString();
    }
}
