package Service.Writer;

import java.util.HashMap;
import java.util.Map;

public class OutputFileFormatToWriterMapper {

    private Map<String, OutputFileWriterInterface> mapper = new HashMap();

    /**
     * Mapping can be injected through dependency injection also
     * Add more mapping as per need
     */
    public OutputFileFormatToWriterMapper() {
        this.mapper.put("xml", new DomXmlFileWriter());
    }

    public Map<String, OutputFileWriterInterface> provideMap() {
        return this.mapper;
    }
}
