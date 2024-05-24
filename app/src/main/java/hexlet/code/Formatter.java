package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.SortedMap;

public class Formatter {

    public static String generateFormatedMessage(Map<String, Object> firstMap,
                                                 Map<String, Object> secondMap,
                                                 SortedMap<String, String> diffMap,
                                                 String format) throws Exception {
        switch (format) {
            case "stylish" :
                return Stylish.generateStylishOutput(firstMap, secondMap, diffMap);
            case "plain" :
                return Plain.generatePlainOutput(firstMap, secondMap, diffMap);
            case "json" :
                return Json.generateJson(firstMap, secondMap, diffMap);
            default:
                return "unknown format";
        }
    }
}
