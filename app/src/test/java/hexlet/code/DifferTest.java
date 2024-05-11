package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static Path pathToResultFile = Path.of("src/test/resources/result.json").toAbsolutePath().normalize();
    static String resultFileContent;

    static {
        try {
            resultFileContent = readString(pathToResultFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws IOException {
        assertEquals(resultFileContent,
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }
}
