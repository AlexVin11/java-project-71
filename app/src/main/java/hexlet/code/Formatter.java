package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String generateFormatedMessage(Map<String, Object> diffMap,
                                                 String format) throws Exception {
        switch (format) {
            case "stylish" :
                return Stylish.generateStylishOutput(diffMap);
            case "plain" :
                return Plain.generatePlainOutput(diffMap);
            case "json" :
                return Json.generateJson(diffMap);
            default:
                return "unknown format";
        }
    }
}
