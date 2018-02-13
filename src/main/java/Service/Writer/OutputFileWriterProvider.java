package Service.Writer;

public class OutputFileWriterProvider {

    private OutputFileFormatToWriterMapper outputFileFormatToWriterMapper;

    public OutputFileWriterProvider(OutputFileFormatToWriterMapper outputFileFormatToWriterMapper) {
        this.outputFileFormatToWriterMapper = outputFileFormatToWriterMapper;
    }

    /**
     * Returns writer based on file format
     */
    public OutputFileWriterInterface provideWriter(String outputFileFormat) {

        OutputFileWriterInterface outputFileWriter = this.outputFileFormatToWriterMapper.provideMap().get(outputFileFormat);
        if (outputFileWriter == null) {
            throw new IllegalArgumentException(
                    String.format("Could not find any writer for given output file format: %s", outputFileFormat)
            );
        }

        return outputFileWriter;
    }
}
