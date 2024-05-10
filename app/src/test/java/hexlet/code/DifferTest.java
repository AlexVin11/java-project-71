package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static String CORRECTRESULTOFFILE1ANDFILE2COMPARE;
    static {
        try {
            CORRECTRESULTOFFILE1ANDFILE2COMPARE = Files.readString(Path.of("test/resources/result.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerate() throws IOException {
        assertEquals(CORRECTRESULTOFFILE1ANDFILE2COMPARE,
                Differ.generate("test/resources/file1.json", "test/resources/file2.json"));
    }
}
