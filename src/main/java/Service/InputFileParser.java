package Service;

import Service.Entity.DecathlonEvent;
import Service.Helper.FileHelper;
import Service.Helper.MinuteSecondsHelper;
import Service.Reader.InputFileReaderInterface;
import Service.Reader.InputFileReaderProvider;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputFileParser {

    private InputFileReaderProvider inputFileReaderProvider;

    public InputFileParser(InputFileReaderProvider inputFileReaderProvider) {
        this.inputFileReaderProvider = inputFileReaderProvider;
    }

    /**
     * Parses the given input file by using certain reader.
     * Reader is found based on file extension
     * <p>
     * Returns collection of event=>score for each athlete
     */
    public Map<String, Map<String, Float>> parseFile(String filePath) throws FileNotFoundException {

        InputFileReaderInterface inputFileReader = this.inputFileReaderProvider.provideReader(
                FileHelper.getFileExtension(filePath)
        );

        List<List<String>> parsedScores = inputFileReader.read(filePath);

        // collection of Athlete name along with it's event => score map
        Map<String, Map<String, Float>> eventScoresByAthletes = new HashMap<>();
        for (List<String> scores : parsedScores) {
            eventScoresByAthletes.put(scores.get(0), this.mapScoreToEvent(scores));
        }

        return eventScoresByAthletes;
    }

    /**
     * Returns collection of event name along with it's score
     * In order to properly save in Float, TRACK_RUN_1500M event score need to be converted into seconds, as
     * it contains 2 dots (e.g 6.51.01)
     */
    private Map<String, Float> mapScoreToEvent(List<String> scores) {

        Map<String, Float> athleteEventScore = new HashMap<>();

        athleteEventScore.put(DecathlonEvent.TRACK_100M.name(), Float.parseFloat(scores.get(1)));
        athleteEventScore.put(DecathlonEvent.FIELD_LONG_JUMP.name(), Float.parseFloat(scores.get(2)));
        athleteEventScore.put(DecathlonEvent.FIELD_SHOT_PUT.name(), Float.parseFloat(scores.get(3)));
        athleteEventScore.put(DecathlonEvent.FIELD_HIGH_JUMP.name(), Float.parseFloat(scores.get(4)));
        athleteEventScore.put(DecathlonEvent.TRACK_RUN_400M.name(), Float.parseFloat(scores.get(5)));
        athleteEventScore.put(DecathlonEvent.TRACK_110M_HURDLES.name(), Float.parseFloat(scores.get(6)));
        athleteEventScore.put(DecathlonEvent.FIELD_DISCUS_THROW.name(), Float.parseFloat(scores.get(7)));
        athleteEventScore.put(DecathlonEvent.FIELD_POLE_VAULT.name(), Float.parseFloat(scores.get(8)));
        athleteEventScore.put(DecathlonEvent.FIELD_JAVELIN_THROW.name(), Float.parseFloat(scores.get(9)));
        athleteEventScore.put(DecathlonEvent.TRACK_RUN_1500M.name(), MinuteSecondsHelper.getInSeconds(scores.get(10)));

        return athleteEventScore;
    }
}
