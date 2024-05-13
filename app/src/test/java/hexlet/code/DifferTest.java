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

    static Path pathToResultForYamlFiles = Path.of("src/test/resources/resultyaml.txt").toAbsolutePath().normalize();
    static String yamlCompareFileContent;

    static {
        try {
            yamlCompareFileContent = readString(pathToResultForYamlFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws IOException {
        assertEquals(jsonCompareFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
        assertEquals(yamlCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
    }
}
