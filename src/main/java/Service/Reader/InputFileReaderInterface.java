package Service.Reader;

import java.io.FileNotFoundException;
import java.util.List;

public interface InputFileReaderInterface {

    /**
     * Parses given file & returns data in List
     * Input file can be provided in ANY format (e.g XML, CSV, PDF, DOC etc) and we can write specific reader for it too
     */
    List<List<String>> read(String filePath) throws FileNotFoundException;
}
