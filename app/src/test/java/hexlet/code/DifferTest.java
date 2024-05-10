package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static String CORRECTRESULTOFFILECOMPARE;
    static {
        try {
            CORRECTRESULTOFFILECOMPARE = Files.readString(Path.of("java-project-71/app/src/test/resources/result.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws IOException {
        assertEquals(CORRECTRESULTOFFILECOMPARE,
                Differ.generate("test/resources/file1.json", "test/resources/file2.json"));
    }
}
