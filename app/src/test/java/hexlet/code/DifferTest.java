package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    static final String CORRECT_RESULT_OF_COMPARING_1 = "{\n"
            + "  - follow: false,\n"
            + "    host: hexlet.io,\n"
            + "  - proxy: 123.234.53.22,\n"
            + "  - timeout: 50,\n"
            + "  + timeout: 20,\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    public void testGenerate() throws IOException {
        assertEquals(Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"),
                Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }
}
