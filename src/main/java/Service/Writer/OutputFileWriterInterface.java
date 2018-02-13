package Service.Writer;

import java.util.Map;

public interface OutputFileWriterInterface {

    /**
     * Data can be written in ANY format
     * Write desired output format specific writer by implementing this interface
     */
    boolean  write(
            Map<String, Integer> athleteSortedScoreResult,
            Map<String, Map<String, Float>> eventScoresByAthletes,
            Map<String, String> placeOfCompetition,
            String outputFilePath);
}
