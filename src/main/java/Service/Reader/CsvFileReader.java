package Service.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvFileReader implements InputFileReaderInterface {

    private String separator;

    CsvFileReader(String separator) {
        this.separator = separator;
    }

    public List<List<String>> read(String filePath) throws FileNotFoundException {

        List<List<String>> parsedScores = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Input file could not be read");
        }

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)));
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            String[] scores = scanner.next().split(this.separator);
            parsedScores.add(new ArrayList<>(Arrays.asList(scores)));
        }
        scanner.close();

        return parsedScores;
    }
}
