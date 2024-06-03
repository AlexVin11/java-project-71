package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static Path pathToStylishResultForFlatFiles = pathNormaliser(
            "src/test/resources/flatstylishresult.txt");
    private static String stylishCompareFlatFileContent;
    private static Path pathToStylishNestedResultFile = pathNormaliser(
            "src/test/resources/nestedstylishresult.txt");
    private static String stylishCompareNestedFileContent;
    private static Path pathToPlainNestedResultFile = pathNormaliser(
            "src/test/resources/nestedplainresult.txt");
    private static String plainCompareNestedFileContent;
    private static Path pathToJsonNestedResultFile = pathNormaliser(
            "src/test/resources/newjsonresult.txt");
    private static String asJsonCompareNestedFileContent;
    private static Path pathToPlainResultForFlatFiles = pathNormaliser(
            "src/test/resources/flatplainresult.txt");
    private static String plainCompareFlatFileContent;

    @BeforeAll
    public static void fileReader() throws Exception {
        stylishCompareFlatFileContent = readString(pathToStylishResultForFlatFiles);
        stylishCompareNestedFileContent = readString(pathToStylishNestedResultFile);
        plainCompareNestedFileContent = readString(pathToPlainNestedResultFile);
        asJsonCompareNestedFileContent = readString(pathToJsonNestedResultFile);
        plainCompareFlatFileContent = readString(pathToPlainResultForFlatFiles);
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
    public void testGenerateFlatJsonStylish() throws Exception {
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "stylish"));
    }

    @Test
    public void testGenerateFlatJsonPlain() throws Exception {
        assertEquals(plainCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "plain"));
    }

    @Test
    public void testGenerateFlatYamlStylish() throws Exception {
        assertEquals(stylishCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml",
                        "stylish"));
    }

    @Test
    public void testGenerateFlatYamlPlain() throws Exception {
        assertEquals(plainCompareFlatFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml",
                        "plain"));
    }

    @Test
    public void testGenerateNestedJsonAsJson() throws Exception {
        assertEquals(asJsonCompareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json",
                        "src/test/resources/nestedfile2.json",
                        "json"));
    }

    public static Path pathNormaliser(String pathToFile) {
        return Path.of(pathToFile).toAbsolutePath().normalize();
    }
}
