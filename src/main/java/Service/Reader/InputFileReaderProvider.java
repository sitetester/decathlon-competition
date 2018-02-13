package Service.Reader;

public class InputFileReaderProvider {

    private InputFileFormatToReaderMapper inputFileFormatToReaderMapper;

    public InputFileReaderProvider(InputFileFormatToReaderMapper inputFileFormatToReaderMapper) {
        this.inputFileFormatToReaderMapper = inputFileFormatToReaderMapper;
    }


    /**
     * Detects input file format and returns reader accordingly
     */
    public InputFileReaderInterface provideReader(String inputFileFormat) {

        InputFileReaderInterface inputFileReader = this.inputFileFormatToReaderMapper.provideMap().get(inputFileFormat);
        if (inputFileReader == null) {
            throw new IllegalArgumentException(
                    String.format("Could not find any reader for given input file format: %s", inputFileFormat)
            );
        }

        return inputFileReader;
    }
}

