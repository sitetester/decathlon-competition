package Service.Reader;

import java.util.HashMap;
import java.util.Map;

public class InputFileFormatToReaderMapper {

    private Map<String, InputFileReaderInterface> mapper = new HashMap();

    /**
     * Mapping can be provided through dependency injection also
     * Add more mapping as per need
     */
    public InputFileFormatToReaderMapper() {
        this.mapper.put("txt", new CsvFileReader(";"));
    }

    public Map<String, InputFileReaderInterface> provideMap() {
        return this.mapper;
    }
}
