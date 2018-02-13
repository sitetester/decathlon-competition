package Service.Reader;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvFileReaderTest {

    @Test
    public void read() {
        CsvFileReader csvFileReader = new CsvFileReader(";");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Decathlon_input.txt").getPath());

        try {
            List<List<String>> parsedLines = csvFileReader.read(file.getPath());

            assertEquals(4, parsedLines.size());

            assertEquals("Siim Susi", parsedLines.get(0).get(0));
            assertEquals("12.61", parsedLines.get(0).get(1));

            assertEquals("Anti Loop", parsedLines.get(3).get(0));
            assertEquals("13.43", parsedLines.get(3).get(1));
            assertEquals("6.51.01", parsedLines.get(3).get(10));

        } catch (FileNotFoundException e) {
            // ...
        }

    }
}