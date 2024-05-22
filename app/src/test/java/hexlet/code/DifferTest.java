package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DifferTest {

    @ParameterizedTest
    @ValueSource(strings = {"json"})
    public void compareFiles(String type) throws Exception {
        var filepath1 = getFixturePath("file1." + type).toString();
        var filepath2 = getFixturePath("file2." + type).toString();
        var format = "plain";
        var actual = Differ.generate(filepath1, filepath2, format);
        var expected = readFile(format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"plain"})
    public void generateDiffInFormat(String format) throws Exception {
        var filepath1 = getFixturePath("file1.json").toString();
        var filepath2 = getFixturePath("file2.json").toString();
        var actual = Differ.generate(filepath1, filepath2, format);
        var expected = readFile(format);
        assertEquals(expected, actual);
    }

    private static Path getFixturePath(String filename) {
        return Paths.get("src", "test", "resources", filename);
    }

    private static String readFile(String filename) throws Exception {
        return Files.readString(getFixturePath(filename));
    }
}