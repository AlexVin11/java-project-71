package hexlet.code.formatters;

import hexlet.code.Parser;

import java.util.Map;
import java.util.SortedMap;

import static hexlet.code.Differ.KEY_STATUS;

public class Plain {

    public static String generatePlainOutput(Map<String, Object> firstFileAsHashMap,
                                             Map<String, Object> secondFileAsHashMap,
                                             SortedMap<String, String> differenceMap) {
        Map<String, String> firstFileAsStringsMap = Parser.convertMapValuesToString(firstFileAsHashMap);
        Map<String, String> secondFileAsStringsMap = Parser.convertMapValuesToString(secondFileAsHashMap);
        StringBuilder resultMessage = new StringBuilder();

        for (String key : differenceMap.keySet()) {
            if (differenceMap.get(key).equals(KEY_STATUS[0])) {
                resultMessage.append("\nProperty '" + key + "' was updated. From ");
                String oldValue = firstFileAsStringsMap.get(key);
                String newValue = secondFileAsStringsMap.get(key);
                if (oldValue.contains("{") || oldValue.contains("[")) {
                    resultMessage.append("[complex value] to ");
                } else {
                    if (String.class.isInstance(firstFileAsHashMap.get(key))) {
                        resultMessage.append("'" + oldValue + "'" + " to ");
                    } else {
                        resultMessage.append(oldValue + " to ");
                    }
                }
                if (newValue.contains("{") || newValue.contains("[")) {
                    resultMessage.append("[complex value]");
                } else {
                    if (String.class.isInstance(secondFileAsHashMap.get(key))) {
                        resultMessage.append("'" + newValue + "'");
                    } else {
                        resultMessage.append(newValue);
                    }
                }
            }
            if (differenceMap.get(key).equals(KEY_STATUS[2])) {
                resultMessage.append("\nProperty '" + key + "' was removed");
            }
            if (differenceMap.get(key).equals(KEY_STATUS[3])) {
                resultMessage.append("\nProperty '" + key + "' was added with value: ");
                String newValue = secondFileAsStringsMap.get(key);
                if (newValue.contains("{") || newValue.contains("[")) {
                    resultMessage.append("[complex value]");
                } else {
                    if (String.class.isInstance(secondFileAsHashMap.get(key))) {
                        resultMessage.append("'" + newValue + "'");
                    } else {
                        resultMessage.append(newValue);
                    }
                }
            }
        }
        return resultMessage.toString();
    }
}
