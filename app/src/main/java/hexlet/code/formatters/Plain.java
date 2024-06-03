package hexlet.code.formatters;

import hexlet.code.ValueAndState;

import java.util.Map;
import java.util.Objects;

import static hexlet.code.Comparator.EDITED;
import static hexlet.code.Comparator.ADDED;
import static hexlet.code.Comparator.REMOVED;


public class Plain {

    public static String generatePlainOutput(Map<String, Object> differenceMap) {
        StringBuilder resultMessage = new StringBuilder();

        for (var entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            ValueAndState valueAndState = (ValueAndState) differenceMap.get(key);
            String status = valueAndState.getKeyStatus();
            if (status.equals(EDITED)) {
                resultMessage.append("Property '" + key + "' was updated. From ");
                Object oldValue = valueAndState.getOldValue();
                Object newValue = valueAndState.getNewValue();
                if (isComplex(oldValue)) {
                    resultMessage.append("[complex value] to ");
                } else {
                    if (String.class.isInstance(oldValue)) {
                        resultMessage.append("'" + oldValue + "'" + " to ");
                    } else {
                        resultMessage.append(oldValue + " to ");
                    }
                }
                if (isComplex(newValue)) {
                    resultMessage.append("[complex value]" + "\n");
                } else {
                    if (String.class.isInstance(newValue)) {
                        resultMessage.append("'" + newValue + "'" + "\n");
                    } else {
                        resultMessage.append(newValue + "\n");
                    }
                }
            }
            if (status.equals(REMOVED)) {
                resultMessage.append("Property '" + key + "' was removed" + "\n");
            }
            if (status.equals(ADDED)) {
                resultMessage.append("Property '" + key + "' was added with value: ");
                Object newValue = valueAndState.getNewValue();
                if (isComplex(newValue)) {
                    resultMessage.append("[complex value]" + "\n");
                } else {
                    if (String.class.isInstance(newValue)) {
                        resultMessage.append("'" + newValue + "'" + "\n");
                    } else {
                        resultMessage.append(newValue + "\n");
                    }
                }
            }
        }
        return resultMessage.delete(resultMessage.length() - 1, resultMessage.length()).toString();
    }

    public static boolean isComplex(Object content) {
        if (Objects.isNull(content)) {
            return false;
        }
        if (content.toString().contains("{") || content.toString().contains("[")) {
            return true;
        }
        return false;
    }
}
