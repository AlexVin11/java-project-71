package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static Path pathToStylishNestedResultFile = pathNormaliser(
            "src/test/resources/nestedstylishresult.txt");
    private static String stylishCompareNestedFileContent;
    private static Path pathToPlainNestedResultFile = pathNormaliser(
            "src/test/resources/nestedplainresult.txt");
    private static String plainCompareNestedFileContent;
    private static Path pathToJsonNestedResultFile = pathNormaliser(
            "src/test/resources/newjsonresult.txt");
    private static String asJsonCompareNestedFileContent;

    @BeforeAll
    public static void fileReader() throws Exception {
        stylishCompareNestedFileContent = readString(pathToStylishNestedResultFile);
        plainCompareNestedFileContent = readString(pathToPlainNestedResultFile);
        asJsonCompareNestedFileContent = readString(pathToJsonNestedResultFile);
    }

    @Test
    public void testGenerateNestedJsonFilesDefaultFormat() throws Exception {
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                "src/test/resources/nestedfile2.json"));
    }

    @Test
    public void testGenerateNestedYamlFilesDefaultFormat() throws Exception {
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml",
                "src/test/resources/nestedfile2.yaml"));
    }

    @Test
    public void testGenerateNestedJsonStylish() throws Exception {
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.json",
                        "stylish"));
    }

    @Test
    public void testGenerateNestedJsonPlain() throws Exception {
        assertEquals(plainCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.json",
                        "plain"));
    }

    @Test
    public void testGenerateNestedJsonAsJson() throws Exception {
        assertEquals(asJsonCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.json",
                        "json"));
    }

    @Test
    public void testGenerateNestedYamlStylish() throws Exception {
        assertEquals(stylishCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml",
                        "src/test/resources/nestedfile2.yaml",
                        "stylish"));
    }

    @Test
    public void testGenerateNestedYamlPlain() throws Exception {
        assertEquals(plainCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml",
                        "src/test/resources/nestedfile2.yaml",
                        "plain"));
    }

    @Test
    public void testGenerateNestedYamlAsJson() throws Exception {
        assertEquals(asJsonCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml",
                        "src/test/resources/nestedfile2.yaml",
                        "json"));
    }

    public static Path pathNormaliser(String pathToFile) {
        return Path.of(pathToFile).toAbsolutePath().normalize();
    }
}
