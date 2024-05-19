package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static Path pathToResultForJsonFiles = Path.of("src/test/resources/resultjson.txt").toAbsolutePath().normalize();
    static String jsonCompareFileContent;

    static {
        try {
            jsonCompareFileContent = readString(pathToResultForJsonFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static Path pathToNestedResultJsonFile = Path.of("src/test/resources/nestedresult.txt").toAbsolutePath().normalize();
    static String compareNestedFileContent;

    static {
        try {
            compareNestedFileContent =readString(pathToNestedResultJsonFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws Exception {
        assertEquals(jsonCompareFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
        assertEquals(jsonCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
        assertEquals(jsonCompareFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.yaml"));
        assertEquals(compareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.json"));
        assertEquals(compareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.yaml", "src/test/resources/nestedfile2.yaml"));
        assertEquals(compareNestedFileContent,
                Differ.generate("src/test/resources/nestedfile1.json", "src/test/resources/nestedfile2.yaml"));
    }
}
