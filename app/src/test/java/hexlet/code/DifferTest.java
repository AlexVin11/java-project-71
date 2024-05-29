package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static Path pathToStylishResultForFlatFiles = Path.of("src/test/resources/flatstylishresult.txt")
            .toAbsolutePath().normalize();
    private static String stylishCompareFlatFileContent;
    private static Path pathToStylishNestedResultFile = Path.of("src/test/resources/nestedstylishresult.txt")
            .toAbsolutePath().normalize();
    private static String stylishCompareNestedFileContent;
    private static Path pathToPlainNestedResultFile = Path.of("src/test/resources/nestedplainresult.txt")
            .toAbsolutePath().normalize();
    private static String plainCompareNestedFileContent;
    private static Path pathToJsonNestedResultFile = Path.of("src/test/resources/newjsonresult.txt")
            .toAbsolutePath().normalize();
    private static String compareNestedFileContentAsJson;
    private static Path pathToPlainResultForFlatFiles = Path.of("src/test/resources/flatplainresult.txt")
            .toAbsolutePath().normalize();
    private static String plainCompareFlatFileContent;

    static {
        try {
            stylishCompareFlatFileContent = readString(pathToStylishResultForFlatFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            stylishCompareNestedFileContent = readString(pathToStylishNestedResultFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            plainCompareNestedFileContent = readString(pathToPlainNestedResultFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            compareNestedFileContentAsJson = readString(pathToJsonNestedResultFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            plainCompareFlatFileContent = readString(pathToPlainResultForFlatFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws Exception {
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.yaml"));
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json",
                        "stylish"));
        assertEquals(plainCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json",
                        "plain"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.json"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml", "src/test/resources/nestedfile2.yaml"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.yaml",
                        "stylish"));
        assertEquals(plainCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.yaml",
                        "plain"));
        assertEquals(compareNestedFileContentAsJson,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.yaml",
                        "json"));
    }
}
