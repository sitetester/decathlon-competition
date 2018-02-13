package Service.Writer;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OutputFileWriterProviderTest {

    @Test
    public void provideWriter() {
        OutputFileWriterProvider outputFileWriterProvider = new OutputFileWriterProvider(
                new OutputFileFormatToWriterMapper()
        );

        assertTrue(outputFileWriterProvider.provideWriter("xml") instanceof DomXmlFileWriter);
    }
}