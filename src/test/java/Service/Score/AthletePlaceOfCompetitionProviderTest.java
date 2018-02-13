package Service.Score;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AthletePlaceOfCompetitionProviderTest {

    @Test
    public void providePlaceOfCompetitionForActualUseCase() {

        Map<String, Integer> athleteSortedScoreResult = new LinkedHashMap<>();

        athleteSortedScoreResult.put("Siim Susi", 4203);
        athleteSortedScoreResult.put("Jaana Lind", 3496);
        athleteSortedScoreResult.put("Beata Kana", 3201);
        athleteSortedScoreResult.put("Anti Loop", 3099);

        Map<String, String> placeOfCompetition = new AthletePlaceOfCompetitionProvider()
                .providePlaceOfCompetition(athleteSortedScoreResult);

        assertEquals("1", placeOfCompetition.get("Siim Susi"));
        assertEquals("2", placeOfCompetition.get("Jaana Lind"));
        assertEquals("3", placeOfCompetition.get("Beata Kana"));
        assertEquals("4", placeOfCompetition.get("Anti Loop"));
    }

    @Test
    public void providePlaceOfCompetitionForPossiblyDuplicatePositions() {

        Map<String, Integer> athleteSortedScoreResult = new LinkedHashMap<>();

        athleteSortedScoreResult.put("A", 1000);
        athleteSortedScoreResult.put("B", 1000);

        athleteSortedScoreResult.put("C", 900);
        athleteSortedScoreResult.put("D", 900);

        athleteSortedScoreResult.put("E", 800);
        athleteSortedScoreResult.put("F", 700);

        Map<String, String> placeOfCompetition = new AthletePlaceOfCompetitionProvider()
                .providePlaceOfCompetition(athleteSortedScoreResult);

        assertEquals("1-2", placeOfCompetition.get("A"));
        assertEquals("1-2", placeOfCompetition.get("B"));

        assertEquals("3-4", placeOfCompetition.get("C"));
        assertEquals("3-4", placeOfCompetition.get("D"));

        assertEquals("5", placeOfCompetition.get("E"));
        assertEquals("6", placeOfCompetition.get("F"));
    }
}