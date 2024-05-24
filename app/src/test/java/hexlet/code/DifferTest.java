package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static Path pathToStylishResultForFlatFiles = Path.of("src/test/resources/flatstylishresult.txt")
            .toAbsolutePath().normalize();
    static String stylishCompareFlatFileContent;
    static Path pathToStylishNestedResultFile = Path.of("src/test/resources/nestedstylishresult.txt")
            .toAbsolutePath().normalize();
    static String stylishCompareNestedFileContent;
    static Path pathToPlainNestedResultFile = Path.of("src/test/resources/nestedplainresult.txt")
            .toAbsolutePath().normalize();
    static String plainCompareNestedFileContent;
    static Path pathToJsonNestedResultFile = Path.of("src/test/resources/jsonresult.txt")
            .toAbsolutePath().normalize();
    static String compareNestedFileContentAsJson;

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

    @Test
    public void testGenerate() throws Exception {
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.yaml"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.json"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml", "src/test/resources/nestedfile2.yaml"));
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.yaml"));
        assertEquals(plainCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.yaml", "plain"));
        assertEquals(compareNestedFileContentAsJson,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.yaml", "json"));
    }
}
